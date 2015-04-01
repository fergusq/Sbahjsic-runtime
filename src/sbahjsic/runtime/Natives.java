package sbahjsic.runtime;

import sbahjsic.runtime.type.SFunc;
import sbahjsic.runtime.type.SString;

/** Contains implementations of declarable native resources.*/
public final class Natives {
	
	private Natives() { throw new AssertionError(); }
	
	public static SValue load(String resource) {
		switch(resource) {
			
			case "ns":
				return new SFunc((con, args) -> {
					SFunc.requireArguments(0, args.length);
					return new SString("" + System.nanoTime());
				});
				
			case "ms":
				return new SFunc((con, args) -> {
					SFunc.requireArguments(0, args.length);
					return new SString("" + System.currentTimeMillis());
				});
				
			default:
				throw new UnknownNativeException("Unknown native resource " + resource);
		}
	}
}