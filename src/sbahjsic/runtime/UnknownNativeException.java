package sbahjsic.runtime;

import sbahjsic.core.SbahjsicException;

/** Indicates that an unknown native resource was encountered.*/
public final class UnknownNativeException extends SbahjsicException {

	private static final long serialVersionUID = -7560434314422104494L;

	public UnknownNativeException(String description) {
		super(description);
	}
	
}