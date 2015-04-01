package sbahjsic.runtime;

import java.util.HashMap;
import java.util.Map;

import sbahjsic.runtime.type.SBool;
import sbahjsic.runtime.type.SFunc;
import sbahjsic.runtime.type.SInt;
import sbahjsic.runtime.type.SNull;
import sbahjsic.runtime.type.SString;
import sbahjsic.runtime.type.SVoid;

public final class DefaultFunctions {
	
	private final static Map<String, String> HELP = new HashMap<>();
	
	private DefaultFunctions() {
		throw new AssertionError();
	}
	
	private static void setHelp(String value, String help) {
		HELP.put(value, help);
	}
	
	public static Map<String, SValue> get() {
		Map<String, SValue> map = new HashMap<>();
		
		map.put("null", SNull.INSTANCE);
		map.put("true", SBool.TRUE);
		map.put("false", SBool.FALSE);
		map.put("_void", SVoid.INSTANCE);
		
		setHelp("print", "Prints something.");
		map.put("print", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			System.out.println(args[0]);
			return SVoid.INSTANCE;
		}));
		
		setHelp("typeof", "Returns the type of its argument.");
		map.put("typeof", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			return new SString(args[0].typeName());
		}));
		
		setHelp("_string", "Converts its argument to string.");
		map.put("_string", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			return new SString(args[0].asString());
		}));
		
		setHelp("_bool", "Converts its argument to bool.");
		map.put("_bool", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			return args[0].asBool() ? SBool.TRUE : SBool.FALSE;
		}));
		
		setHelp("_int", "Converts its argument to int.");
		map.put("_int", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			return new SInt(args[0].asInt());
		}));
		
		setHelp("setHelp", "Sets the help text of some value.");
		map.put("setHelp", new SFunc((con, args) -> {
			SFunc.requireArguments(2, args.length);
			setHelp(args[0].asString(), args[1].asString());
			return SVoid.INSTANCE;
		}));
		
		setHelp("help", "Returns the help text of some value.");
		map.put("help", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			System.out.println(HELP.get(args[0].asString()));
			return SVoid.INSTANCE;
		}));
		
		return map;
	}
}