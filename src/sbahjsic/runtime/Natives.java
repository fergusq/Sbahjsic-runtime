package sbahjsic.runtime;

import sbahjsic.runtime.type.SFloat;
import sbahjsic.runtime.type.SFunc;
import sbahjsic.runtime.type.SNull;
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
				
			case "sin":
				return new SFunc((con, args) -> {
					SFunc.requireArguments(1, args.length);
					return new SFloat((float) Math.sin(args[0].asFloat()));
				});
				
			case "cos":
				return new SFunc((con, args) -> {
					SFunc.requireArguments(1, args.length);
					return new SFloat((float) Math.cos(args[0].asFloat()));
				});
				
			case "tan":
				return new SFunc((con, args) -> {
					SFunc.requireArguments(1, args.length);
					return new SFloat((float) Math.tan(args[0].asFloat()));
				});
				
			case "asin":
				return new SFunc((con, args) -> {
					SFunc.requireArguments(1, args.length);
					return new SFloat((float) Math.asin(args[0].asFloat()));
				});
				
			case "acos":
				return new SFunc((con, args) -> {
					SFunc.requireArguments(1, args.length);
					return new SFloat((float) Math.acos(args[0].asFloat()));
				});
				
			case "atan":
				return new SFunc((con, args) -> {
					SFunc.requireArguments(1, args.length);
					return new SFloat((float) Math.atan(args[0].asFloat()));
				});
				
			case "sum":
				return new SFunc((con, args) -> {
					if(args.length == 0) { return SNull.INSTANCE; }
					SValue val = args[0];
					for(int i = 1; i < args.length; i++) {
						val = val.callOperator(con, "+", args[i]);
					}
					return val;
				});
				
			default:
				throw new UnknownNativeException("Unknown native resource " + resource);
		}
	}
}