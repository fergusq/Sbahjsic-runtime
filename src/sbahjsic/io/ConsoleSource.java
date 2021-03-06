package sbahjsic.io;

import java.util.Scanner;

import sbahjsic.parser.lexer.Lexer;
import sbahjsic.parser.syntaxtree.Node;
import sbahjsic.parser.syntaxtree.NodeType;
import sbahjsic.parser.syntaxtree.Parser;

/** A ScriptSource that reads System.in.*/
public final class ConsoleSource implements ScriptSource {
	
	public final static Scanner SCANNER = new Scanner(System.in);
	
	private final static NodeType[] CONTINUE_AFTER = new NodeType[] { NodeType.IF_NODE,
		NodeType.WHILE_NODE, NodeType.FUNCTION_NODE};
	private final static NodeType[] END_AFTER = new NodeType[] { NodeType.ENDIF_NODE,
		NodeType.ENDWHILE_NODE, NodeType.ENDFUNCTION_NODE };
	
	private boolean called = false;
	private int level = 0;

	@Override
	public boolean hasMore() { return !called || level != 0; }

	@Override
	public String nextLine() {
		String line = SCANNER.nextLine();
		Node node = new Parser(Lexer.toTokens(line)).parse();
		
		for(NodeType type : CONTINUE_AFTER) {
			if(type == node.type()) { level++; }
		}
		
		for(NodeType type : END_AFTER) {
			if(type == node.type()) { level--; }
		}
		
		called = true;
		return line;
	}

	@Override
	public void close() {}

	@Override
	public String getName() {
		return "Console";
	}
	
}