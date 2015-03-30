package sbahjsic.core;

/** Responsible for handling all errors and warnings..*/
public final class Errors {
	
	/** Puts a warning.
	 * @param warning the type of warning*/
	public static void warn(Warning warning) {
		System.err.println("WARNING: " + warning.warnString);
	}
	
	/** Puts a warning with a detail message.
	 * @param warning the type of warning
	 * @param message the additional detail message*/
	public static void warn(Warning warning, String message) {
		System.err.println("WARNING: " + warning.warnString  + ": " + message);
	}
	
	/** Puts an error contained in a SbahjsicException.
	 * @param exc the exception*/
	public static void error(SbahjsicException exc) {
		System.err.println(exc.getClass().getSimpleName() + ": " + exc.getDescription());
		System.exit(1);
	}
	
	/** Puts an internal error contained in a Throwable.
	 * @param thr the throwable*/
	public static void internalError(Throwable thr) {
		if(thr instanceof SbahjsicException) {
			error((SbahjsicException) thr);
		}
		System.err.println("Internal error: " + thr);
		System.exit(1);
	}
	
	public static enum Warning {
		NONSTANDARD_FILE_EXTENSION("Nonstandard file extension");
		
		private final String warnString;
		
		Warning(String warnString) {
			this.warnString = warnString;
		}
	}
}