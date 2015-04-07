package sbahjsic.core;

import java.util.ArrayList;
import java.util.List;

import sbahjsic.core.Errors.Warning;

/** Represents data parsed from the program arguments.*/
public final class Arguments {
	
	private boolean debug = false;
	private boolean saveLineNumbers = true;
	private boolean fixLongs = false;
	private boolean fixFloats = false;
	private final List<String> files = new ArrayList<>();
	
	private Arguments() {}
	
	/** Returns whether debug mode is enabled.
	 * @return whether debug mode is enabled*/
	public boolean isDebugMode() { return debug; }
	
	/** Returns whether line numbers are saved in instructions for
	 * debug purposes.
	 * @return whether line numbers are saved*/
	public boolean saveLineNumbers() {
		return saveLineNumbers;
	}
	
	/** Returns whether longs work properly.
	 * @return whether longs work properly*/
	public boolean fixLongs() {
		return fixLongs;
	}
	
	/** Returns whether floats work properly.
	 * @return whether floats work properly*/
	public boolean fixFloats() {
		return fixFloats;
	}
	
	/** Returns all files that should be run.
	 * @return all files that should be run*/
	public List<String> getFiles() { return files; }
	
	public static Arguments parse(String[] args) {
		Arguments arguments = new Arguments();
		
		for(String arg : args) {
			if(arg.equals("-d")) { 
				arguments.debug = true;
			} else if(arg.equals("-nln")) {
				arguments.saveLineNumbers = false;
			} else if(arg.equals("-fix--longs")) {
				arguments.fixLongs = true;
			} else if(arg.equals("-fix--floats")) {
				arguments.fixFloats = true;
			} else {
				if(!arg.endsWith(".sb")) {
					Errors.warn(Warning.NONSTANDARD_FILE_EXTENSION, arg);
				}
				arguments.files.add(arg);
			}
		}
		
		return arguments;
	}
}