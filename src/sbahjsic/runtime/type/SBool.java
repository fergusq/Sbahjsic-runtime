package sbahjsic.runtime.type;

/** Represents a boolean.*/
public final class SBool extends AbstractType {
	
	public final static SBool TRUE = new SBool();
	public final static SBool FALSE = new SBool();
	
	static {
		TRUE.registerUnOperator("!", (con, b) -> b.asBool() ? FALSE : TRUE);
		
		TRUE.registerBiOperator("&", (con, a1, a2) -> a1.asBool() && a2.asBool() ? TRUE : FALSE);
		TRUE.registerBiOperator("|", (con, a1, a2) -> a1.asBool() || a2.asBool() ? TRUE : FALSE);
		TRUE.registerBiOperator("^", (con, a1, a2) -> a1.asBool() ^ a2.asBool() ? TRUE : FALSE);
		
		TRUE.registerBiOperator("==", (con, a1, a2) -> a1.asBool() == a2.asBool() ? TRUE : FALSE);
		TRUE.registerBiOperator("!=", (con, a1, a2) -> a1.asBool() != a2.asBool() ? TRUE : FALSE);
	}

	@Override
	public String typeName() {
		return "bool";
	}

	@Override
	public int asInt() {
		return this == TRUE ? 1 : 0;
	}
	
	@Override
	public boolean asBool() {
		return this == TRUE;
	}

	@Override
	public String asString() {
		return this == TRUE ? "true" : "false";
	}

	@Override
	public boolean equals(Object o) {
		return o == this;
	}

	@Override
	public int hashCode() {
		return this == TRUE ? 1 : 0;
	}
	
}