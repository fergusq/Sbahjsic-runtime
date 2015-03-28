package sbahjsic.runtime;

import sbahjsic.parser.compiler.Instruction;

public final class Executor {
	
	public static void execute(Instruction[] instructions) {
		RuntimeContext context = new RuntimeContext();
		
		for(Instruction i : instructions)
			i.execute(context);
		
		System.out.println(context.pop());
	}
}