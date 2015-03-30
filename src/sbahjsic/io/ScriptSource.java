package sbahjsic.io;

/** A source that provides Sbahjsic code.*/
public interface ScriptSource extends AutoCloseable {
	
	/** Returns whether more lines exist.
	 * @return whether more lines exist*/
	public boolean hasMore();
	
	/** Returns the next line if {@code hasMore()} returns
	 * {@code true}. Otherwise throws some exception.
	 * @return the next line*/
	public String nextLine();
	
	/** Returns a string describing this {@code ScriptSource}.
	 * @return the name of this {@code ScriptSource}*/
	public String getName();
	
	/** Closes this {@code ScriptSource}.*/
	@Override
	public void close();
}