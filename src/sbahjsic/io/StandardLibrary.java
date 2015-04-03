package sbahjsic.io;

import java.io.File;
import java.io.FileNotFoundException;

import sbahjsic.core.Errors;
import sbahjsic.core.Errors.Warning;
import sbahjsic.runtime.ExecutionEnvironment;

public class StandardLibrary {
	
	private final static String STD = "std";
	
	public static void checkExistance() {
		File std = new File(STD);
		
		if(!std.exists() || std.isFile()) {
			Errors.warn(Warning.NO_STANDARD_LIBRARY);
		}
	}
	
	public static void load(String packageName, ExecutionEnvironment env) {
		try {
			env.execute(new FileSource(new File(STD + "/" + packageName + ".sb")));
		} catch(FileNotFoundException e) {
			throw new LibraryNotFoundException(packageName);
		} catch(Exception e) {
			Errors.internalError(e);
		}
	}
}