package sbahjsic.runtime;

import sbahjsic.runtime.type.SFunc;
import sbahjsic.runtime.type.SString;

/** Contains implementations of declarable native resources.*/
public final class Natives {
	
	private Natives() { throw new AssertionError(); }
	
	public static SValue load(String resource) {
		System.out.println("Loading " + resource);
		switch(resource) {
			
			case "sin":
				return new SFunc((con, args) -> {
					SFunc.requireArguments(1, args.length);
					return new SString("" + Math.sin(args[0].asDouble()));
				});
				
			default:
				throw new UnknownNativeException("Unknown native resource " + resource);
		}
	}
}