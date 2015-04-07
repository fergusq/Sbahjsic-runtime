package sbahjsic.runtime.type;

import sbahjsic.runtime.CallException;
import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.SValue;

/** Represents a function.*/
public final class SFunc extends AbstractType {
	
	static {
		SFunc dummy = new SFunc(null);
		
		dummy.registerBiOperator("==", (con, a1, a2) -> {
			a1 = toSFuncIfPossible(a1);
			a2 = toSFuncIfPossible(a2);
			return a1 == a2 ? SBool.TRUE : SBool.FALSE;
		});
		dummy.registerBiOperator("!=", (con, a1, a2) -> {
			a1 = toSFuncIfPossible(a1);
			a2 = toSFuncIfPossible(a2);
			return a1 != a2 ? SBool.TRUE : SBool.FALSE;
		});
		
		dummy.registerBiOperator("*", (con, a1, a2) -> {
			return a1.call(con, new SValue[] { a2 });
		});
	}
	
	private static SValue toSFuncIfPossible(SValue value) {
		return value instanceof SRef ? toSFuncIfPossible(((SRef) value).refersTo) : value;
	}
	
	private final SbahjsicFunction func;
	
	public SFunc(SbahjsicFunction function) {
		this.func = function;
	}
	
	@Override
	public SValue call(RuntimeContext context, SValue... args) {
		return func.apply(context, args);
	}

	@Override
	public String typeName() {
		return "func";
	}

	@Override
	public int asInt() {
		return -1;
	}

	@Override
	public String asString() {
		return "func@" + System.identityHashCode(this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((func == null) ? 0 : func.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SFunc)) {
			return false;
		}
		SFunc other = (SFunc) obj;
		if (func == null) {
			if (other.func != null) {
				return false;
			}
		} else if (!func.equals(other.func)) {
			return false;
		}
		return true;
	}
	
	public static void requireArguments(int required, int actual) {
		if(required != actual)
			throw new CallException("Expected " + required + " arguments, got " + actual);
	}

	@FunctionalInterface
	public static interface SbahjsicFunction {
		
		public SValue apply(RuntimeContext context, SValue... args);
	}
}