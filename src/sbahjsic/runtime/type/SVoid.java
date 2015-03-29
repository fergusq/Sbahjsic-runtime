package sbahjsic.runtime.type;

/** An empty return value from functions.*/
public final class SVoid extends AbstractType {
	
	public final static SVoid INSTANCE = new SVoid();
	
	static {
		INSTANCE.registerBiOperator("==", (con, a1, a2) -> SBool.FALSE);
		INSTANCE.registerBiOperator("!=", (con, a1, a2) -> SBool.TRUE);
	}
	
	private SVoid() {}

	@Override
	public String typeName() {
		return "void";
	}

	@Override
	public int asInt() { return -1; }

	@Override
	public String asString() { return "void"; }

	@Override
	public boolean equals(Object o) { return o == this; }

	@Override
	public int hashCode() { return -9236456; }
	
}