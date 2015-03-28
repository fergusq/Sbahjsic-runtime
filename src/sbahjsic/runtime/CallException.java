package sbahjsic.runtime;

import sbahjsic.core.SbahjsicException;

/** Indicates that there was an error while calling a value.*/
public final class CallException extends SbahjsicException {

	private static final long serialVersionUID = 3006800595867943876L;

	public CallException(String description) {
		super(description);
	}
	
}