package sbahjsic.parser.compiler;

import sbahjsic.runtime.RuntimeContext;

/** An instruction executed by the runtime.*/
public abstract class Instruction {
	
	public final static InstructionType[] CONTROL_FLOW = new InstructionType[] {
		InstructionType.IF,
		InstructionType.ELSE,
		InstructionType.ENDIF,
		InstructionType.WHILE,
		InstructionType.ENDWHILE,
		InstructionType.LINE_NUMBER
	};
	
	/** Executes this instruction.
	 * @param runtime the runtime context*/
	public abstract void execute(RuntimeContext runtime);
	
	@Override
	public abstract String toString();
	
	/** Returns the type of this instruction.
	 * @return the type of this instruction*/
	public abstract InstructionType type();
	
	/** Pushes a single integer into the stack.
	 * @param value the integer to push
	 * @return an appropriate instruction*/
	public static Instruction pushInt(int value) {
		return new PushInt(value);
	}
	
	/** Pushes a float into the stack.
	 * @param value the float to push
	 * @return an appropriate instruction*/
	public static Instruction pushFloat(float value) {
		return new PushFloat(value);
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
	
	/** Switches whether instructions of an if block are executed.
	 * @return an appropriate instruction*/
	public static Instruction ifElse() {
		return Else.INSTANCE;
	}
	
	/** Declares a native resource.
	 * @param resource the native resource
	 * @return an appropriate instruction*/
	public static Instruction nativeDeclaration(String resource) {
		return new Native(resource);
	}
	
	/** Imports a resource.
	 * @param resource the resource to import
	 * @return an appropriate instruction*/
	public static Instruction importStatement(String resource) {
		return new Import(resource);
	}
	
	/** Starts a while statement.
	 * @param conditionInstructions the amount of instructions in the condition of
	 * the resulting while loop
	 * @return an appropriate instruction*/
	public static Instruction whileStatement(int conditionInstructions) {
		return new While(conditionInstructions);
	}
	
	/** Ends a while statement.
	 * @return an appropriate instruction*/
	public static Instruction endWhileStatement() {
		return EndWhile.INSTANCE;
	}
	
	/** Starts defining a function. Pops its name and the names of its
	 * arguments.
	 * @param args the number of arguments the function takes
	 * @return an appropriate instruction*/
	public static Instruction function(int args) {
		return new Function(args);
	}
	
	/** Ends defining a function.
	 * @return an appropriate instruction*/
	public static Instruction endFunction() {
		return EndFunction.INSTANCE;
	}
	
	/** Returns from a function, popping the return value.
	 * @return an appropriate instruction*/
	public static Instruction returnStatement() {
		return Return.INSTANCE;
	}
}