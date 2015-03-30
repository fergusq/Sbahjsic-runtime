package sbahjsic.core;

/** An exception that occurred due to malformed Sbahjsic code.
 * 
 * <p>Each contains an user-readable description that will be printed
 * when the exception occurs at runtime.*/
public class SbahjsicException extends RuntimeException {

	private static final long serialVersionUID = -6881539339493720862L;
	
	private String description;
	
	/** Creates an instance.
	 * @param description a helpful description of the occurred error*/
	protected SbahjsicException(String description) {
		this.description = description + " at";
	}
	
	/** Returns the description of the occurred problem.
	 * @return the description*/
	public String getDescription() {
		return description;
	}
	
	/** Adds a stack level to the error message.
	 * @param lineNumber the line the error occurred at
	 * @param source the source of the bad line*/
	public void addStackLevel(int lineNumber, String source) {
		description += "\n\t" + source + " : " + lineNumber;
	}
	
	@Override
	public final String getMessage() {
		return description;
	}
	
}