package sbahjsic.io;

import java.util.Scanner;

/** A ScriptSource that reads System.in.*/
public final class ConsoleSource implements ScriptSource {
	
	private final Scanner scanner = new Scanner(System.in);

	@Override
	public boolean hasMore() { return true; }

	@Override
	public String nextLine() { return scanner.nextLine(); }
	
}