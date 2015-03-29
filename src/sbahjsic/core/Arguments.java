package sbahjsic.core;

import java.util.ArrayList;
import java.util.List;

/** Represents data parsed from the program arguments.*/
public final class Arguments {
	
	private boolean debug = false;
	private final List<String> files = new ArrayList<>();
	
	private Arguments() {}
	
	/** Returns whether debug mode is enabled.
	 * @return whether debug mode is enabled*/
	public boolean isDebugMode() { return debug; }
	
	/** Returns all files that should be run.
	 * @return all files that should be run*/
	public List<String> getFiles() { return files; }
	
	public static Arguments parse(String[] args) {
		Arguments arguments = new Arguments();
		
		for(String arg : args) {
			if(arg.equals("-d")) { 
				arguments.debug = true;
			} else {
				arguments.files.add(arg);
			}
		}
		
		return arguments;
	}
}