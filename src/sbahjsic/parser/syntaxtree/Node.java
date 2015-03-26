package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;

/** A node in the syntax tree.*/
public abstract class Node {
	
	/** Returns the type of this node.
	 * @return the type of this node*/
	public abstract NodeType type();
	
	public abstract Instruction[] toInstructions();
	
	@Override
	public abstract String toString();
	
	@Override
	public abstract boolean equals(Object o);
	
	@Override
	public abstract int hashCode();
}