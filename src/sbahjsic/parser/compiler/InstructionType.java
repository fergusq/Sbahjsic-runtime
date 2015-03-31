package sbahjsic.parser.compiler;

/** The types of instructions.*/
public enum InstructionType {
	BINARY_OPERATOR,
	UNARY_OPERATOR,
	FUNCTION_CALL,
	IF,
	ENDIF,
	LINE_NUMBER,
	PUSH_INT,
	PUSH_STRING,
	PUSH_VAR
}