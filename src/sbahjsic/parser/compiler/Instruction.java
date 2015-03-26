package sbahjsic.parser.compiler;

/** An instruction executed by the runtime.*/
public abstract class Instruction {
	
	Instruction() {}
	
	@Override
	public abstract String toString();
	
	/** Pushes a single integer into the stack.
	 * @param value the integer to push
	 * @return an appropriate instruction*/
	public static Instruction pushInt(int value) {
		return new PushInt(value);
	}
	
	/** Pushes a string into the stack.
	 * @param value the string to push
	 * @return an appropriate instruction*/
	public static Instruction pushString(String value) {
		return new PushString(value);
	}
	
	/** Pushes a variable into the stack.
	 * @param value the variable to push
	 * @return an appropriate instruction*/
	public static Instruction pushVar(String value) {
		return new PushVar(value);
	}
	
	/** Performs an unary operator on the top value of the stack.
	 * @param op the unary operator
	 * @return an appropriate instruction*/
	public static Instruction unaryOperator(String op) {
		return new UnaryOperator(op);
	}
	
	/** Performs a binary operator on the top two values of the stack.
	 * The first value in the stack becomes the first argument, and so on.
	 * @param op the binary operator
	 * @return an appropriate instruction*/
	public static Instruction binaryOperator(String op) {
		return new BinaryOperator(op);
	}
	
	/** Calls a function, which is the top value of the stack.
	 * {@code args} parameters are read from the stack and added to the call. The
	 * first value in the stack becomes the first argument, and so on.
	 * @param args the number of arguments in the call
	 * @return an appropriate instruction*/
	public static Instruction functionCall(int args) {
		return new FunctionCall(args);
	}
	
	/** Starts an if block according to the top value in the stack.
	 * @return an appropriate instruction*/
	public static Instruction ifStart() {
		return If.INSTANCE;
	}
	
	/** Ends an if block.
	 * @return an appropriate instruction*/
	public static Instruction endIf() {
		return EndIf.INSTANCE;
	}
}