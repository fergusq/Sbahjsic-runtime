package sbahjsic.runtime.type;

/** Represents a long.*/
public final class SLong extends AbstractType {
	
	static {
		SLong dummy = new SLong(0);
		
		dummy.registerBiOperator("+", (con, a1, a2) -> new SLong(a1.asLong() + a2.asLong()));
		dummy.registerBiOperator("-", (con, a1, a2) -> new SLong(a1.asLong() - a2.asLong()));
		dummy.registerBiOperator("*", (con, a1, a2) -> new SLong(a1.asLong() * a2.asLong()));
		dummy.registerBiOperator("/", (con, a1, a2) -> { 
			Long divider = a2.asLong();
			if(divider == 0) { return SUndefined.INSTANCE; }
			return new SLong(a1.asLong() / divider);
		});
		dummy.registerBiOperator("%", (con, a1, a2) -> new SLong(a1.asLong() % a2.asLong()));
		
		dummy.registerBiOperator("**", (con, a1, a2) -> new SLong((long) Math.pow(a1.asLong(), a2.asLong())));
		
		dummy.registerBiOperator("&", (con, a1, a2) -> new SLong(a1.asLong() & a2.asLong()));
		dummy.registerBiOperator("|", (con, a1, a2) -> new SLong(a1.asLong() | a2.asLong()));
		dummy.registerBiOperator("^", (con, a1, a2) -> new SLong(a1.asLong() ^ a2.asLong()));
		dummy.registerUnOperator("~", (con, arg) -> new SLong(~arg.asLong()));
		
		dummy.registerBiOperator("<", (con, a1, a2) -> a1.asLong() < a2.asLong() ? SBool.TRUE : SBool.FALSE);
		dummy.registerBiOperator(">", (con, a1, a2) -> a1.asLong() > a2.asLong() ? SBool.TRUE : SBool.FALSE);
		dummy.registerBiOperator("<=", (con, a1, a2) -> a1.asLong() <= a2.asLong() ? SBool.TRUE : SBool.FALSE);
		dummy.registerBiOperator(">=", (con, a1, a2) -> a1.asLong() >= a2.asLong() ? SBool.TRUE : SBool.FALSE);
		
		dummy.registerBiOperator("==", (con, a1, a2) -> a1.asLong() == a2.asLong() ? SBool.TRUE : SBool.FALSE);
		dummy.registerBiOperator("!=", (con, a1, a2) -> a1.asLong() != a2.asLong() ? SBool.TRUE : SBool.FALSE);
	}
	
	private final long value;
	
	public SLong(long value) {
		this.value = value;
	}

	@Override
	public String typeName() {
		return "long";
	}
	
	@Override
	public long asLong() { return value; }
	
	@Override
	public float asFloat() { return value; }
	
	@Override
	public double asDouble() { return value; }

	@Override
	public int asInt() { return (int) value; }

	@Override
	public String asString() {
		return "" + value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (value ^ (value >>> 32));
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
		if (!(obj instanceof SLong)) {
			return false;
		}
		SLong other = (SLong) obj;
		if (value != other.value) {
			return false;
		}
		return true;
	}
}
