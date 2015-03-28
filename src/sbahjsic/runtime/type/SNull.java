package sbahjsic.runtime.type;

public final class SNull extends AbstractType {
	
	public final static SNull INSTANCE = new SNull();

	@Override
	public String typeName() { return "null"; }

	@Override
	public int asInt() { return 0; }

	@Override
	public String asString() { return "null"; }
	
}