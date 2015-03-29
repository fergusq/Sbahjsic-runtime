package sbahjsic.runtime.type;

public final class SNull extends AbstractType {
	
	public final static SNull INSTANCE = new SNull();
	
	static {
		INSTANCE.registerBiOperator("==", (con, a1, a2) 
				-> (a2.asInt() == 0 || a2.asString().equalsIgnoreCase("null"))
				? SBool.TRUE : SBool.FALSE);
		
		INSTANCE.registerBiOperator("!=", (con, a1, a2) 
				-> (a2.asInt() != 0 && !a2.asString().equalsIgnoreCase("null"))
				? SBool.TRUE : SBool.FALSE);
	}
	
	private SNull() {}

	@Override
	public String typeName() { return "null"; }

	@Override
	public int asInt() { return 0; }

	@Override
	public String asString() { return "null"; }

	@Override
	public boolean equals(Object o) { return o == INSTANCE; }

	@Override
	public int hashCode() { return -34657; }
	
}