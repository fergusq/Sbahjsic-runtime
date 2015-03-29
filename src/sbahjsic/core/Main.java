package sbahjsic.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

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
				} catch(FileNotFoundException e) {
					fail("File not found: " + file);
				} catch(UnsupportedEncodingException e) {
					fail("Cannot read " + file);
				} catch(Exception e) {
					fail(e.getMessage());
				}
			}
		}
	}
	
	private static void execute(ExecutionPlan plan, ScriptSource source) {
		try {
			plan.execute(source);
		} catch(SbahjsicException e) {
			fail(e.getDescription());
		} catch(Exception e) {
			System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
			e.printStackTrace();
		} finally {
			source.close();
		}
	}
	
	private static void fail(String message) {
		System.out.println(message);
	}
}