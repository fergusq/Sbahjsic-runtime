package sbahjsic.parser.syntaxtree;

import sbahjsic.core.SbahjsicException;

/** Indicates that a syntax error happened.*/
public final class SyntaxException extends SbahjsicException {

	private static final long serialVersionUID = 5358514234620966059L;

	protected SyntaxException(String description) {
		super(description);
	}
	
}