package sbahjsic.io;

/** A ScriptSource that returns a pre-defined set of lines.*/
public final class MockSource implements ScriptSource {
	
	private final String[] lines;
	private int index = 0;
	
	/** Creates an instance.
	 * @param lines the lines to return from {@code nextLine()}*/
	public MockSource(String... lines) {
		this.lines = lines;
	}

	@Override
	public boolean hasMore() { return index < lines.length; }

	@Override
	public String nextLine() {
		return lines[index++];
	}

	@Override
	public void close() {}
}