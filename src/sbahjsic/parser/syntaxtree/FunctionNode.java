package sbahjsic.parser.syntaxtree;

import java.util.Arrays;

import sbahjsic.parser.compiler.Instruction;

public final class FunctionNode extends Node {
	
	private final String name;
	private final String[] args;
	
	public FunctionNode(String name, String[] args) {
		this.name = name;
		this.args = args;
	}

	@Override
	public NodeType type() {
		return NodeType.FUNCTION_NODE;
	}

	@Override
	public Instruction[] toInstructions() {
		Instruction[] instructions = new Instruction[args.length + 2];
		
		for(int i = 0; i < args.length; i++) {
			instructions[i] = Instruction.pushVar(args[args.length - 1 - i]);
		}
		
		instructions[instructions.length - 2] = Instruction.pushVar(name);
		instructions[instructions.length - 1] = Instruction.function(args.length);
		
		return instructions;
	}

	@Override
	public String toString() {
		return "FUNCTION{"+name+", " + Arrays.toString(args) + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(args);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof FunctionNode)) {
			return false;
		}
		FunctionNode other = (FunctionNode) obj;
		if (!Arrays.equals(args, other.args)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}