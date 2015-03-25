package sbahjsic.parser.syntaxtree;

import java.util.List;

import sbahjsic.parser.lexer.Token;

/** The contents of a single line in tree form.*/
public final class SyntaxTree {
	
	private final Node mainNode;
	
	public SyntaxTree(List<Token> tokens) {
		mainNode = new Parser(tokens).parse();
	}
	
	/** Returns the main node of this syntax tree.
	 * @return the main node*/
	public Node mainNode() { return mainNode; }
	
	@Override
	public String toString() {
		return mainNode.toString();
	}
}