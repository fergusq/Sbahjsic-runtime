package sbahjsic.core;

import java.io.File;

import sbahjsic.io.ConsoleSource;
import sbahjsic.io.FileSource;
import sbahjsic.io.ScriptSource;
import sbahjsic.runtime.ExecutionPlan;

public final class Main {
	
	@SuppressWarnings("resource")
	public static void main(String[] programArgs) {
		
		Arguments args = Arguments.parse(programArgs);
		
		System.out.println("Sbahjsic v0.0.0");
		
		ExecutionPlan plan = new ExecutionPlan()
				.forLineValues(args.isDebugMode() ? 
						v -> {} : 
						v -> System.out.println(">> " + v))
						
				.forInstructions(args.isDebugMode() ? 
						i -> System.out.println(i) : 
						i -> {})
						
				.setRunCode(!args.isDebugMode());
		
		if(args.getFiles().isEmpty()) {
			execute(plan, new ConsoleSource());
		} else {
			for(String file : args.getFiles()) {
				try {
					execute(plan, new FileSource(new File(file)));
				} catch(Exception e) {
					Errors.internalError(e);
				}
			}
		}
	}
	
	private static void execute(ExecutionPlan plan, ScriptSource source) {
		try {
			plan.execute(source);
		} catch(Exception e) {
			Errors.internalError(e);
		} finally {
			source.close();
		}
	}
}