package sbahjsic.parser.compiler;

/** The types of instructions.*/
public enum InstructionType {
	BINARY_OPERATOR,
	UNARY_OPERATOR,
	FUNCTION_CALL,
	IF,
	ELSE,
	ENDIF,
	LINE_NUMBER,
	PUSH_INT,
	PUSH_LONG,
	PUSH_STRING,
	PUSH_FLOAT,
	PUSH_DOUBLE,
	PUSH_VAR,
	NATIVE,
	IMPORT,
	WHILE,
	ENDWHILE,
	FUNCTION,
	ENDFUNCTION,
	RETURN
}