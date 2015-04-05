package sbahjsic.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import sbahjsic.core.Errors;
import sbahjsic.core.SbahjsicException;
import sbahjsic.io.ScriptSource;
import sbahjsic.parser.compiler.Instruction;
import sbahjsic.parser.compiler.LineNumber;
import sbahjsic.parser.lexer.Lexer;
import sbahjsic.parser.syntaxtree.SyntaxTree;
import sbahjsic.runtime.type.SVoid;

/** Capable of executing scripts, saving their state for further executions.*/
public final class ExecutionEnvironment {
	
	private Consumer<Instruction> instConsumer = null;
	private Consumer<SValue> valConsumer = null;
	private boolean runCode = true;
	private boolean saveLineNumbers = true;
	private final RuntimeContext context;
	private int index = 0;
	
	/** Creates an instance.*/
	public ExecutionEnvironment() {
		context = new RuntimeContext();
	}
	
	/** Creates an instance with a specific {@code RuntimeContext}.
	 * @param context the {@code RuntimeContext}*/
	public ExecutionEnvironment(RuntimeContext context) {
		this.context = context;
	}
	
	/** Accepts a consumer that receives all compiled instructions.
	 * @param consumer the consumer
	 * @return itself, for chaining*/
	public ExecutionEnvironment forInstructions(Consumer<Instruction> consumer) {
		instConsumer = consumer;
		return this;
	}
	
	/** Accepts a consumer that receives the last values in the stacks of things ran.
	 * @param consumer the consumer
	 * @return itself, for chaining*/
	public ExecutionEnvironment forLastValue(Consumer<SValue> consumer) {
		valConsumer = consumer;
		return this;
	}
	
	/** Sets whether the received code will be run, affecting the internal state.
	 * @param runCode whether code will be run
	 * @return itself, for chaining*/
	public ExecutionEnvironment setRunCode(boolean runCode) {
		this.runCode = runCode;
		return this;
	}
	
	/** Sets whether line numbers are saved in compiled instructions.
	 * @param saveLineNums whether line numbers will be saved
	 * @return itself, for chaining*/
	public ExecutionEnvironment setSaveLineNumbers(boolean saveLineNums) {
		saveLineNumbers = saveLineNums;
		return this;
	}
	
	/** Runs the contents of some {@code ScriptSource}.
	 * @param source the source*/
	public void execute(ScriptSource source) {
		int lineNumber = 0;
		
		try {
			List<Instruction[]> instructions = new ArrayList<>();
			
			while(source.hasMore()) {
				String line = source.nextLine();
				lineNumber++;
				
				Instruction[] instrs;
				try {
					instrs = new SyntaxTree(Lexer.toTokens(line)).mainNode().toInstructions();
				} catch(SbahjsicException e) {
					e.addStackLevel(lineNumber, source.getName());
					throw e;
				}
				
				if(saveLineNumbers) {
					instrs = addLineNumber(instrs, lineNumber);
				}
				
				instructions.add(instrs);
			}
			
			int requiredSpace = 0;
			for(Instruction[] instrs : instructions)
				requiredSpace += instrs.length;
			
			Instruction[] allInstructions = new Instruction[requiredSpace];
			
			int lastIndex = 0;
			for(Instruction[] ins : instructions) {
				System.arraycopy(ins, 0, allInstructions, lastIndex, ins.length);
				lastIndex += ins.length;
			}
			
			Optional<SValue> result = execute(source.getName(), allInstructions);
			
			if(valConsumer != null && result.isPresent() && !(result.get() instanceof SVoid)) {
				valConsumer.accept(result.get());
			}
			
		} catch(SbahjsicException e) {
			throw e;
		} catch(Exception e) {
			Errors.internalError(e);
		}
	}
	
	/** Runs some specific instructions.
	 * @param source the source where the instructions came from, visible in error messages
	 * @param instructions the instructions
	 * @return the (possible) last value in the stack*/
	public Optional<SValue> execute(String source, Instruction[] instructions) {
		Consumer<Scope> newScopeListener = s -> {
			s.setStart(index);
		};
		
		Consumer<Scope> scopeEndListener = s -> {
			if(s.savesInstructions()) {
				s.getInstructionsConsumer().accept(context, s.getSavedInstructions());
			}
			
			if(s.loops() && s.isExecuted()) {
				if(!context.scopeStack().top().loops()) {
					index = s.getStart() - s.getJumpsBack() -1;
				}
			}
		};
		
		context.scopeStack().addNewScopeListener(newScopeListener);
		context.scopeStack().addScopeEndListener(scopeEndListener);
		
		try {
			for(index = 0; index < instructions.length; index++) {
				Instruction ins = instructions[index];
				
				executeInstruction(ins);
			}
		} catch(SbahjsicException e) {
			e.addStackLevel(context.getLineNumber(), source);
			throw e;
		} finally {
			context.scopeStack().removeNewScopeListener(newScopeListener);
			context.scopeStack().removeScopeEndListener(scopeEndListener);
		}
		
		return context.peek();
	}
	
	private void executeInstruction(Instruction ins) {
		Scope top = context.scopeStack().top();
		
		if(canBeRun(ins))
			ins.execute(context);
		
		// If a new scope starts, it wont save its definition instruction
		Scope newTop = context.scopeStack().top();
		if(top == newTop && top.savesInstructions())
			top.saveInstruction(ins);
		
		if(instConsumer != null)
			instConsumer.accept(ins);
	}
	
	private static Instruction[] addLineNumber(Instruction[] ins, int line) {
		Instruction[] newIns = new Instruction[ins.length + 1];
		newIns[0] = new LineNumber(line);
		System.arraycopy(ins, 0, newIns, 1, ins.length);
		return newIns;
	}
	
	private boolean canBeRun(Instruction ins) {
		Scope top = context.scopeStack().top();
		return runCode && (context.scopeStack().executeInstructions() || 
				top.getPermittedInstructions().contains(ins.type()));
	}
}