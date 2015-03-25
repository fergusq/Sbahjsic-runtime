package sbahjsic.parser.syntaxtree;

/** An endif node.*/
public final class EndIfNode extends Node {

	@Override
	public NodeType type() { return NodeType.ENDIF_NODE; }

	@Override
	public String toString() { return "endif"; }

	@Override
	public boolean equals(Object o) {
		return o instanceof EndIfNode;
	}
	
	@Override
	public int hashCode() {
		return 346566;
	}
	
}