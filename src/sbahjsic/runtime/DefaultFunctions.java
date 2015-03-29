package sbahjsic.runtime;

import java.util.HashMap;
import java.util.Map;

import sbahjsic.runtime.type.SBool;
import sbahjsic.runtime.type.SFunc;
import sbahjsic.runtime.type.SNull;
import sbahjsic.runtime.type.SString;
import sbahjsic.runtime.type.SVoid;

public final class DefaultFunctions {
	
	private DefaultFunctions() {
		throw new AssertionError();
	}
	
	public static Map<String, SValue> get() {
		Map<String, SValue> map = new HashMap<>();
		
		map.put("null", SNull.INSTANCE);
		map.put("true", SBool.TRUE);
		map.put("false", SBool.FALSE);
		
		map.put("print", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			System.out.println(args[0]);
			return SVoid.INSTANCE;
		}));
		
		map.put("typeof", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			return new SString(args[0].typeName());
		}));
		
		return map;
	}
}