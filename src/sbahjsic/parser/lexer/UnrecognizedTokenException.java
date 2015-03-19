package sbahjsic.parser.lexer;

import sbahjsic.core.SbahjsicException;

/** Signifies that the lexer encountered a token it didn't understand.*/
public class UnrecognizedTokenException extends SbahjsicException {
	
	private static final long serialVersionUID = 8220368389276630038L;

	protected UnrecognizedTokenException(String badToken) {
		super("Unrecognized token \"" + badToken + "\".");
	}
	
}