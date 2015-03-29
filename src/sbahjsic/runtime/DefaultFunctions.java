package sbahjsic.runtime;

import java.util.HashMap;
import java.util.Map;

import sbahjsic.runtime.type.SBool;
import sbahjsic.runtime.type.SNull;

public final class DefaultFunctions {
	
	private DefaultFunctions() {
		throw new AssertionError();
	}
	
	public static Map<String, SValue> get() {
		Map<String, SValue> map = new HashMap<>();
		
		map.put("null", SNull.INSTANCE);
		map.put("true", SBool.TRUE);
		map.put("false", SBool.FALSE);
		
		return map;
	}
}