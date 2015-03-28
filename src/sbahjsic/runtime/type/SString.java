package sbahjsic.runtime.type;

public final class SString extends AbstractType {

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
	
}