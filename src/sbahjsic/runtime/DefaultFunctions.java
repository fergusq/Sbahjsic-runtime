package sbahjsic.runtime;

import java.util.HashMap;
import java.util.Map;

import sbahjsic.runtime.type.SBool;
import sbahjsic.runtime.type.SFloat;
import sbahjsic.runtime.type.SFunc;
import sbahjsic.runtime.type.SInt;
import sbahjsic.runtime.type.SNull;
import sbahjsic.runtime.type.SString;
import sbahjsic.runtime.type.SUndefined;
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
		map.put("_undefined", SUndefined.INSTANCE);
		
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
		
		setHelp("_float", "Converts its argument to float.");
		map.put("_float", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			return new SFloat(args[0].asFloat());
		}));
		
		setHelp("setHelp", "Sets the help text of some value.");
		map.put("setHelp", new SFunc((con, args) -> {
			SFunc.requireArguments(2, args.length);
			setHelp(args[0].asAddress(), args[1].asString());
			return SVoid.INSTANCE;
		}));
		
		setHelp("help", "Returns the help text of some value.");
		map.put("help", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			System.out.println(HELP.get(args[0].asAddress()));
			return SVoid.INSTANCE;
		}));
		
		setHelp("list", "Prints all defined variables.");
		map.put("list", new SFunc((con, args) -> {
			SFunc.requireArguments(0, args.length);
			con.forMemoryValues((string, val) -> {
				System.out.println("  ["+string+"] -> [" + 
						(HELP.containsKey(string) ? HELP.get(string) 
						: val.asString()) + "]");
			});
			return SVoid.INSTANCE;
		}));
		
		setHelp("del", "Deletes something from the memory.");
		map.put("del", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			con.deleteMemory(args[0].asAddress());
			return SVoid.INSTANCE;
		}));
		
		setHelp("ParseInt", "Parses its argument as an int. Returns undefined if failure.");
		map.put("ParseInt", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			try {
				return new SInt(Integer.parseInt(args[0].asString()));
			} catch(Exception e) {
				return SUndefined.INSTANCE;
			}
		}));
		
		setHelp("ParseDec", "Parses a decimal. Returns undefined if failure.");
		map.put("ParseDec", new SFunc((con, args) -> {
			SFunc.requireArguments(1, args.length);
			try {
				return new SFloat(Float.parseFloat(args[0].asString()));
			} catch(Exception e) {
				return SUndefined.INSTANCE;
			}
		}));
		
		return map;
	}
}