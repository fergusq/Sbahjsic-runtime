package sbahjsic.parser.syntaxtree;

/** The types of nodes.*/
public enum NodeType {
	EMPTY,
	INT_LITERAL,
	STRING_LITERAL,
	IDENTIFIER,
	FUNCTION_CALL,
	UNARY_OPERATOR,
	BINARY_OPERATOR,
	IF_NODE,
	ENDIF_NODE
}