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

public final class ExecutionEnvironment {
	
	private Consumer<Instruction> instConsumer = null;
	private Consumer<SValue> valConsumer = null;
	private boolean runCode = true;
	private final RuntimeContext context = new RuntimeContext();
	
	public ExecutionEnvironment forInstructions(Consumer<Instruction> consumer) {
		instConsumer = consumer;
		return this;
	}
	
	public ExecutionEnvironment forLastValue(Consumer<SValue> consumer) {
		valConsumer = consumer;
		return this;
	}
	
	public ExecutionEnvironment setRunCode(boolean runCode) {
		this.runCode = runCode;
		return this;
	}
	
	public void execute(ScriptSource source) {
		int lineNumber = 0;
		
		try {
			List<Instruction[]> instructions = new ArrayList<>();
			
			while(source.hasMore()) {
				String line = source.nextLine();
				lineNumber++;
				
				Instruction[] instrs = 
						new SyntaxTree(Lexer.toTokens(line)).mainNode().toInstructions();
				
				instrs = addLineNumber(instrs, lineNumber);
				
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
			
			try {
				Optional<SValue> result = execute(source.getName(), allInstructions);
				
				if(valConsumer != null && result.isPresent() && !(result.get() instanceof SVoid)) {
					valConsumer.accept(result.get());
				}
			} catch(SbahjsicException e) {
				Errors.error(e);
			}
			
		} catch(SbahjsicException e) {
			e.addStackLevel(lineNumber, source.getName());
			Errors.error(e);
		} catch(Exception e) {
			Errors.internalError(e);
		}
	}
	
	public Optional<SValue> execute(String source, Instruction[] instructions) {
		try {
			for(Instruction ins : instructions) {
				if(runCode)
					ins.execute(context);
				
				if(instConsumer != null)
					instConsumer.accept(ins);
			}
		} catch(SbahjsicException e) {
			e.addStackLevel(context.getLineNumber(), source);
			throw e;
		} catch(Exception e) {
			Errors.internalError(e);
		}
		
		return context.safePop();
	}
	
	private static Instruction[] addLineNumber(Instruction[] ins, int line) {
		Instruction[] newIns = new Instruction[ins.length + 1];
		newIns[0] = new LineNumber(line);
		System.arraycopy(ins, 0, newIns, 1, ins.length);
		return newIns;
	}
}