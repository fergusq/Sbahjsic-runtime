package sbahjsic.io;

/** A source that provides Sbahjsic code.*/
public interface ScriptSource {
	
	/** Returns whether more lines exist.
	 * @return whether more lines exist*/
	public boolean hasMore();
	
	/** Returns the next line if {@code hasMore()} returns
	 * {@code true}. Otherwise throws some exception.
	 * @return the next line*/
	public String nextLine();
}