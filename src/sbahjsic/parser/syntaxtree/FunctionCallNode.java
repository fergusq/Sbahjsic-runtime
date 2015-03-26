package sbahjsic.parser.syntaxtree;

import java.util.Arrays;

import sbahjsic.parser.compiler.Instruction;
import sbahjsic.parser.lexer.Token;
import sbahjsic.parser.lexer.TokenType;

public final class FunctionCallNode extends ValueNode {
	
	private final String function;
	private final ValueNode[] args;
	
	public FunctionCallNode(Token function, ValueNode[] args) {
		if(function.type() != TokenType.IDENTIFIER) {
			throw new IllegalArgumentException();
		}
		
		this.function = function.string();
		this.args = args;
	}

	@Override
	public NodeType type() { return NodeType.FUNCTION_CALL; }
	
	@Override
	public Instruction[] toInstructions() {
		Instruction[][] argInstructions = new Instruction[args.length][];
		int totalSpace = 2;
		
		for(int i = 0; i < args.length; i++) {
			argInstructions[i] = args[i].toInstructions();
			totalSpace += argInstructions[i].length;
		}
		
		Instruction[] instructions = new Instruction[totalSpace];
		int nextIndex = 0;
		
		for(int i = argInstructions.length-1; i >= 0; i--) {
			Instruction[] argInstruction = argInstructions[i];
			
			System.arraycopy(argInstruction, 0, instructions, nextIndex, argInstruction.length);
			nextIndex += argInstruction.length;
		}
		
		instructions[instructions.length - 2] = Instruction.pushVar(function);
		instructions[instructions.length - 1] = Instruction.functionCall(args.length);
		
		return instructions;
	}

	@Override
	public String toString() {
		return function + "{" + argsString() + "}";
	}
	
	String argsString() {
		if(args.length == 0) { return ""; }
		
		StringBuilder sb = new StringBuilder();
		int maxIndex = args.length -1;
		
		for(int i = 0; i < args.length; i++) {
			ValueNode arg = args[i];
			sb.append(arg);
			if(i == maxIndex) { return sb.toString(); }
			sb.append(", ");
		}
		
		throw new AssertionError();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(args);
		result = prime * result
				+ ((function == null) ? 0 : function.hashCode());
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
		if (!(obj instanceof FunctionCallNode)) {
			return false;
		}
		FunctionCallNode other = (FunctionCallNode) obj;
		if (!Arrays.equals(args, other.args)) {
			return false;
		}
		if (function == null) {
			if (other.function != null) {
				return false;
			}
		} else if (!function.equals(other.function)) {
			return false;
		}
		return true;
	}
	
}