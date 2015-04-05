package sbahjsic.parser.syntaxtree;

/** The types of nodes.*/
public enum NodeType {
	EMPTY,
	INT_LITERAL,
	STRING_LITERAL,
	FLOAT_LITERAL,
	IDENTIFIER,
	FUNCTION_CALL,
	UNARY_OPERATOR,
	BINARY_OPERATOR,
	IF_NODE,
	ELSE_NODE,
	ENDIF_NODE,
	NATIVE_NODE,
	IMPORT_NODE,
	WHILE_NODE,
	ENDWHILE_NODE,
	FUNCTION_NODE,
	ENDFUNCTION_NODE,
	RETURN_NODE
}