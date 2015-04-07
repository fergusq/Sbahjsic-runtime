package sbahjsic.core;

import java.io.File;

import sbahjsic.io.ConsoleSource;
import sbahjsic.io.FileSource;
import sbahjsic.io.ScriptSource;
import sbahjsic.io.StandardLibrary;
import sbahjsic.runtime.ExecutionEnvironment;

public final class Main {
	
	public static Arguments ARGS;
	
	public static void main(String[] programArgs) {
		
		Arguments args = Arguments.parse(programArgs);
		ARGS = args;
		
		System.out.println("Sbahjsic v0.0.0");
		
		StandardLibrary.checkExistance();
		
		ExecutionEnvironment plan = new ExecutionEnvironment()
				.forLastValue(args.isDebugMode() ? 
						v -> {} : 
						v -> System.out.println(">> " + v))
						
				.forInstructions(args.isDebugMode() ? 
						i -> System.out.println(i) : 
						i -> {})
						
				.setSaveLineNumbers(args.saveLineNumbers())
						
				.setRunCode(!args.isDebugMode());
		
		if(args.getFiles().isEmpty()) {
			while(true) {
				try(ConsoleSource cs = new ConsoleSource()) {
					while(cs.hasMore()) {
						execute(plan, cs);
					}
				}
			}
		}
		
		for(String file : args.getFiles()) {
			try {
				execute(plan, new FileSource(new File(file)));
			} catch(Exception e) {
				Errors.internalError(e);
			}
		}
	}
	
	private static void execute(ExecutionEnvironment plan, ScriptSource source) {
		try {
			plan.execute(source);
		} catch(SbahjsicException e) {
			Errors.error(e);
		} catch(Exception e) {
			Errors.internalError(e);
		} finally {
			source.close();
		}
	}
}