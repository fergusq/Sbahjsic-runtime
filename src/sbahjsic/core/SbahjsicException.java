package sbahjsic.core;

/** An exception that occurred due to malformed Sbahjsic code.
 * 
 * <p>Each contains an user-readable description that will be printed
 * when the exception occurs at runtime.*/
public class SbahjsicException extends RuntimeException {

	private static final long serialVersionUID = -6881539339493720862L;
	
	private final String description;
	
	/** Creates an instance.
	 * @param description a helpful description of the occurred error*/
	protected SbahjsicException(String description) {
		this.description = description;
	}
	
	/** Returns the description of the occurred problem.
	 * @return the description*/
	public String getDescription() {
		return description;
	}
	
	@Override
	public final String getMessage() {
		return description;
	}
	
}