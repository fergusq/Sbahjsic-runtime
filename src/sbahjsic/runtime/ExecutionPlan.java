package sbahjsic.runtime;

import java.util.Optional;
import java.util.function.Consumer;

import sbahjsic.core.Errors;
import sbahjsic.core.SbahjsicException;
import sbahjsic.io.ScriptSource;
import sbahjsic.parser.compiler.Instruction;
import sbahjsic.parser.lexer.Lexer;
import sbahjsic.parser.syntaxtree.SyntaxTree;
import sbahjsic.runtime.type.SVoid;

public final class ExecutionPlan {
	
	private Consumer<Instruction> instConsumer = null;
	private Consumer<SValue> valConsumer = null;
	private boolean runCode = true;
	
	public ExecutionPlan forInstructions(Consumer<Instruction> consumer) {
		instConsumer = consumer;
		return this;
	}
	
	public ExecutionPlan forLineValues(Consumer<SValue> consumer) {
		valConsumer = consumer;
		return this;
	}
	
	public ExecutionPlan setRunCode(boolean runCode) {
		this.runCode = runCode;
		return this;
	}
	
	public void execute(ScriptSource source) {
		try {
			RuntimeContext context = new RuntimeContext();
			
			while(source.hasMore()) {
				String line = source.nextLine();
				if(line.trim().isEmpty()) { continue; }
				
				Instruction[] instructions = 
						new SyntaxTree(Lexer.toTokens(line)).mainNode().toInstructions();
				
				for(Instruction ins : instructions) {
					if(runCode)
						ins.execute(context);
					
					if(instConsumer != null)
						instConsumer.accept(ins);
				}
				
				Optional<SValue> lineValue = context.safePop();
				
				if(valConsumer != null && lineValue.isPresent() && lineValue.get() != SVoid.INSTANCE)
					valConsumer.accept(lineValue.get());
			}
		} catch(SbahjsicException e) {
			Errors.error(e);
		} catch(Exception e) {
			Errors.internalError(e);
		}
	}
}