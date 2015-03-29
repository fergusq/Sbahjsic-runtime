package sbahjsic.runtime.type;

public final class SInt extends AbstractType {
	
	static {
		SInt dummy = new SInt(0);
		
		dummy.registerBiOperator("+", (con, a1, a2) -> new SInt(a1.asInt() + a2.asInt()));
		dummy.registerBiOperator("-", (con, a1, a2) -> new SInt(a1.asInt() - a2.asInt()));
		dummy.registerBiOperator("*", (con, a1, a2) -> new SInt(a1.asInt() * a2.asInt()));
		dummy.registerBiOperator("/", (con, a1, a2) -> new SInt(a1.asInt() / a2.asInt()));
		
		dummy.registerBiOperator("**", (con, a1, a2) -> new SInt((int) Math.pow(a1.asInt(), a2.asInt())));
		
		dummy.registerBiOperator("&", (con, a1, a2) -> new SInt(a1.asInt() & a2.asInt()));
		dummy.registerBiOperator("|", (con, a1, a2) -> new SInt(a1.asInt() | a2.asInt()));
		dummy.registerBiOperator("^", (con, a1, a2) -> new SInt(a1.asInt() ^ a2.asInt()));
		dummy.registerUnOperator("~", (con, arg) -> new SInt(~arg.asInt()));
		
		dummy.registerBiOperator("==", (con, a1, a2) -> a1.asInt() == a2.asInt() ? SBool.TRUE : SBool.FALSE);
		dummy.registerBiOperator("!=", (con, a1, a2) -> a1.asInt() != a2.asInt() ? SBool.TRUE : SBool.FALSE);
	}
	
	private final int value;
	
	public SInt(int value) {
		this.value = value;
	}

	@Override
	public String typeName() { return "int"; }

	@Override
	public int asInt() { return value; }

	@Override
	public String asString() { return "" + value; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
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
		if (!(obj instanceof SInt)) {
			return false;
		}
		SInt other = (SInt) obj;
		if (value != other.value) {
			return false;
		}
		return true;
	}
	
}