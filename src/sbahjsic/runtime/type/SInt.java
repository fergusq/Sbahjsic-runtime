package sbahjsic.runtime.type;

public final class SInt extends AbstractType {
	
	static {
		SInt dummy = new SInt(0);
		
		dummy.registerBiOperator("+", (a1, a2) -> new SInt(a1.asInt() + a2.asInt()));
		dummy.registerBiOperator("-", (a1, a2) -> new SInt(a1.asInt() - a2.asInt()));
		dummy.registerBiOperator("*", (a1, a2) -> new SInt(a1.asInt() * a2.asInt()));
		dummy.registerBiOperator("/", (a1, a2) -> new SInt(a1.asInt() / a2.asInt()));
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
	
}