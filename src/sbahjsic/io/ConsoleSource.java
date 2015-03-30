package sbahjsic.io;

import java.util.Scanner;

/** A ScriptSource that reads System.in.*/
public final class ConsoleSource implements ScriptSource {
	
	private final Scanner scanner = new Scanner(System.in);
	private boolean called = false;

	@Override
	public boolean hasMore() { return !called; }

	@Override
	public String nextLine() {
		called = true;
		return scanner.nextLine(); 
	}

	@Override
	public void close() {}

	@Override
	public String getName() {
		return "Console";
	}
	
}