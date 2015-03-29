package sbahjsic.runtime.type;

public final class SString extends AbstractType {
	
	static {
		SString dummy = new SString(null);
		
		dummy.registerBiOperator("+", (con, a1, a2) -> new SString(a1.asString() + a2.asString()));
		
		dummy.registerBiOperator("==", (con, a1, a2) -> a1.asString().equals(a2.asString())
				? SBool.TRUE : SBool.FALSE);
		dummy.registerBiOperator("!=", (con, a1, a2) -> !a1.asString().equals(a2.asString())
				? SBool.TRUE : SBool.FALSE);

	}

	private final String value;
	
	public SString(String value) {
		this.value = value;
	}
	
	@Override
	public String typeName() { return "string"; }

	@Override
	public int asInt() { return value.length(); }

	@Override
	public String asString() { return value; }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (!(obj instanceof SString)) {
			return false;
		}
		SString other = (SString) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
	
}