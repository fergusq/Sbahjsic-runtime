package sbahjsic.parser.syntaxtree;

import sbahjsic.parser.compiler.Instruction;

public final class ImportNode extends Node {
	
	private final String lib;
	
	public ImportNode(String lib) {
		this.lib = lib;
	}

	@Override
	public NodeType type() {
		return NodeType.IMPORT_NODE;
	}

	@Override
	public Instruction[] toInstructions() {
		return new Instruction[] { Instruction.importStatement(lib) };
	}

	@Override
	public String toString() {
		return "IMPORT{" + lib + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lib == null) ? 0 : lib.hashCode());
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
		if (!(obj instanceof ImportNode)) {
			return false;
		}
		ImportNode other = (ImportNode) obj;
		if (lib == null) {
			if (other.lib != null) {
				return false;
			}
		} else if (!lib.equals(other.lib)) {
			return false;
		}
		return true;
	}
}