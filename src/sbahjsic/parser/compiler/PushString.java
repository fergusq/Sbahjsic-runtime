package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.type.SString;

final class PushString extends Instruction {
	
	private final String value;
	
	PushString(String value) {
		this.value = toRawString(value);
	}
	
	@Override
	public void execute(RuntimeContext runtime) {
		runtime.push(new SString(value));
	}

	@Override
	public String toString() {
		return "pshstr '" + value + "'";
	}
	
	static String toRawString(String tokenString) {
		return tokenString.substring(1, tokenString.length()-1).replace("\\\"", "\"");
	}
}