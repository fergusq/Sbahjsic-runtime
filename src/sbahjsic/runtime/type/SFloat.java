package sbahjsic.runtime.type;

/** Represents a floating-point number.*/
public final class SFloat extends AbstractType {
	
	static {
		SFloat dummy = new SFloat(0);
		
		dummy.registerBiOperator("+", (con, a1, a2) -> new SFloat(a1.asFloat() + a2.asFloat()));
		dummy.registerBiOperator("-", (con, a1, a2) -> new SFloat(a1.asFloat() - a2.asFloat()));
		dummy.registerBiOperator("*", (con, a1, a2) -> new SFloat(a1.asFloat() * a2.asFloat()));
		dummy.registerBiOperator("/", (con, a1, a2) -> {
			float result = (a1.asFloat() / a2.asFloat());
			if(Float.isInfinite(result) || Float.isNaN(result)) {
				return SUndefined.INSTANCE;
			}
			return new SFloat(result);
		});
		
		dummy.registerBiOperator("**", (con, a1, a2) -> new SFloat((float) Math.pow(a1.asFloat(), a2.asFloat())));
		
		dummy.registerBiOperator("<", (con, a1, a2) -> a1.asFloat() < a2.asFloat() ? SBool.TRUE : SBool.FALSE);
		dummy.registerBiOperator(">", (con, a1, a2) -> a1.asFloat() > a2.asFloat() ? SBool.TRUE : SBool.FALSE);
		dummy.registerBiOperator("<=", (con, a1, a2) -> a1.asFloat() <= a2.asFloat() ? SBool.TRUE : SBool.FALSE);
		dummy.registerBiOperator(">=", (con, a1, a2) -> a1.asFloat() >= a2.asFloat() ? SBool.TRUE : SBool.FALSE);
		
		dummy.registerBiOperator("==", (con, a1, a2) -> Float.compare(a1.asFloat(), a2.asFloat()) == 0 
				? SBool.TRUE : SBool.FALSE);
		dummy.registerBiOperator("!=", (con, a1, a2) -> Float.compare(a1.asFloat(), a2.asFloat()) != 0 
				? SBool.TRUE : SBool.FALSE);
	}
	
	private final float value;
	
	public SFloat(float value) {
		this.value = value;
	}

	@Override
	public String typeName() {
		return "float";
	}

	@Override
	public int asInt() {
		return (int) value;
	}
	
	@Override
	public float asFloat() { return value; }

	@Override
	public String asString() {
		return "" + value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(value);
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
		if (!(obj instanceof SFloat)) {
			return false;
		}
		SFloat other = (SFloat) obj;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value)) {
			return false;
		}
		return true;
	}
}