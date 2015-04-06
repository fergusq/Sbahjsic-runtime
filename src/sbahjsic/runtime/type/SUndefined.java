package sbahjsic.runtime.type;

/** Represents something undefined.*/
public final class SUndefined extends AbstractType {
	
	public final static SUndefined INSTANCE = new SUndefined();
	
	static {
		INSTANCE.registerBiOperator("==", (con, a1, a2) 
				-> a2 == INSTANCE ? SBool.TRUE : SBool.FALSE);
		
		INSTANCE.registerBiOperator("!=", (con, a1, a2) 
				-> a2 == INSTANCE ? SBool.TRUE : SBool.FALSE);
	}
	
	private SUndefined() {}

	@Override
	public String typeName() {
		return "undefined";
	}

	@Override
	public int asInt() {
		return -1;
	}

	@Override
	public String asString() {
		return "undefined";
	}

	@Override
	public boolean equals(Object o) {
		return o == INSTANCE;
	}

	@Override
	public int hashCode() {
		return 34662;
	}
	
}