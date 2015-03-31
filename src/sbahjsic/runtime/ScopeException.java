package sbahjsic.runtime;

import sbahjsic.core.SbahjsicException;

/** Signifies that the last scope was exited.*/
public final class ScopeException extends SbahjsicException {

	private static final long serialVersionUID = -5955764415358627850L;

	protected ScopeException(String description) {
		super(description);
	}
	
}