package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;

/** The declaration of a native resource.*/
public final class NativeNode extends Node {
	
	private final String resource;
	
	public NativeNode(String resource) {
		this.resource = resource;
	}

	@Override
	public NodeType type() {
		return NodeType.NATIVE_NODE;
	}

	@Override
	public Instruction[] toInstructions() {
		return new Instruction[] { Instruction.nativeDeclaration(resource) };
	}

	@Override
	public String toString() {
		return "NATIVE{" + resource +"}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((resource == null) ? 0 : resource.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof NativeNode)) {
			return false;
		}
		NativeNode other = (NativeNode) obj;
		if (resource == null) {
			if (other.resource != null) {
				return false;
			}
		} else if (!resource.equals(other.resource)) {
			return false;
		}
		return true;
	}
}