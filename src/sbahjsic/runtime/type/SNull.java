package sbahjsic.runtime.type;

public final class SNull extends AbstractType {
	
	public final static SNull INSTANCE = new SNull();
	
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