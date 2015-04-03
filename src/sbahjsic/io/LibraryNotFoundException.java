package sbahjsic.io;

import sbahjsic.core.SbahjsicException;

/** Indicates that an external library wasn't found.*/
public final class LibraryNotFoundException extends SbahjsicException {

	private static final long serialVersionUID = -8205850483368563995L;

	protected LibraryNotFoundException(String libName) {
		super("Library not found: " + libName);
	}
	
}