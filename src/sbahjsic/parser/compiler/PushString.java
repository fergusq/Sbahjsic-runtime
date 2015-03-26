package sbahjsic.parser.compiler;

final class PushString extends Instruction {
	
	private final String value;
	
	PushString(String value) {
		this.value = toRawString(value);
	}

	@Override
	public String toString() {
		return "pshstr " + value;
	}
	
	static String toRawString(String tokenString) {
		return tokenString.substring(1, tokenString.length()-1).replace("\\\"", "\"");
	}
	
}