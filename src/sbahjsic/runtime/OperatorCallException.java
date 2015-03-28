package sbahjsic.runtime;

import sbahjsic.core.SbahjsicException;

/** Indicates that an error happened while calling an operator.*/
public final class OperatorCallException extends SbahjsicException {

	private static final long serialVersionUID = -5147339516944612695L;

	public OperatorCallException(String description) {
		super(description);
	}
	
}