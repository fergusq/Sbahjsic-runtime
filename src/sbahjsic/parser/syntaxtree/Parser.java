package sbahjsic.parser.syntaxtree;

import java.util.ArrayList;
import java.util.List;

import sbahjsic.parser.lexer.Token;
import sbahjsic.parser.lexer.TokenType;

final class Parser {
	
	private List<Token> tokens;
	
	Parser(List<Token> tokens) {
		if(tokens.isEmpty()) { throw new IllegalArgumentException(); }
		this.tokens = tokens;
	}
	
	Node parse() {
		return parse(tokens);
	}
	
	Node parse(List<Token> tokens) {
		Token first = tokens.get(0);
		
		if(first.type() == TokenType.IDENTIFIER) {
			String string = first.string();
			
			if(string.equals("if")) {
				return new IfNode(parseValue(tokens.subList(1, tokens.size())));
			} else if(string.equals("endif") && tokens.size() == 1) {
				return new EndIfNode();
			}
		}
		
		return parseValue(tokens);
	}
	
	static ValueNode parseValue(List<Token> tokens) {
		
		tokens = removeExtraBrackets(tokens);
		
		Token first = tokens.get(0);
		
		if(tokens.size() == 1) {
			return parseValue(first);
		}
		
		if(first.type() == TokenType.OPERATOR) {
			return new UnaryOperatorNode(first, parseValue(tokens.subList(1, tokens.size())));
		}
		
		if(isFunctionCall(tokens)) {
			return parseFunctionCall(tokens);
		}
		
		return parseOperatorStatement(tokens);
	}
	
	static ValueNode parseValue(Token token) {
		switch(token.type()) {
			case INT_LITERAL:
				return new IntNode(token);
			case STRING_LITERAL:
				return new StringNode(token);
			default:
				return new IdentifierNode(token);
		}
	}
	
	static ValueNode parseOperatorStatement(List<Token> tokens) {
		int bracketLevel = 0;
		boolean lastWasValue = false;
		
		for(int i = 0; i < tokens.size(); i++) {
			Token token = tokens.get(i);
			TokenType type = token.type();
			
			if(type == TokenType.BRACKET_OPEN) { bracketLevel++; }
			
			if(bracketLevel == 0) {
				if(lastWasValue && isValue(type)) {
					return new BinaryOperatorNode(Token.operator("*"),
							parseValue(tokens.subList(0, i)),
							parseValue(tokens.subList(i, tokens.size())));
				}
				
				if(type == TokenType.OPERATOR) {
					return new BinaryOperatorNode(token,
							parseValue(tokens.subList(0, i)),
							parseValue(tokens.subList(i+1, tokens.size())));
				}
			}
			
			if(isValue(type)) {
				lastWasValue = true;
			}
			
			if(type == TokenType.BRACKET_CLOSE) { bracketLevel --; }
		}
		
		throw new SyntaxException("Unknown syntax error");
	}
	
	static boolean isValue(TokenType type) {
		return type == TokenType.IDENTIFIER 
				|| type == TokenType.INT_LITERAL 
				|| type == TokenType.STRING_LITERAL;
	}
	
	static List<Token> removeExtraBrackets(List<Token> list) {
		while(hasExtraBrackets(list)) {
			list = list.subList(1, list.size()-1);
		}
		return list;
	}
	
	static boolean hasExtraBrackets(List<Token> list) {
		if(list.get(0).type() != TokenType.BRACKET_OPEN ||
				list.get(list.size()-1).type() != TokenType.BRACKET_CLOSE) {
			return false;
		}
		
		int bracketLevel = 1;
		
		for(int i = 1; i < list.size()-1; i++) {
			TokenType type = list.get(i).type();
			if(type == TokenType.BRACKET_OPEN) { bracketLevel++; }
			if(type == TokenType.BRACKET_CLOSE) { bracketLevel--; }
			if(bracketLevel == 0) { return false; }
		}
		
		return true;
	}
	
	static boolean isFunctionCall(List<Token> tokens) {
		if(tokens.size() < 3) { return false; }
		Token first = tokens.get(0);
		Token second = tokens.get(1);
		Token last = tokens.get(tokens.size()-1);
		
		if(first.type() != TokenType.IDENTIFIER ||
				second.type() != TokenType.BRACKET_OPEN ||
				last.type() != TokenType.BRACKET_CLOSE) { return false; }
		
		return true;
	}
	
	static ValueNode parseFunctionCall(List<Token> tokens) {
		Token function = tokens.get(0);
		List<Token> argumentTokens = tokens.subList(2, tokens.size()-1);
		
		if(argumentTokens.size() == 0) { return new FunctionCallNode(function, new ValueNode[] {}); }
		
		List<ValueNode> args = new ArrayList<>();
		int bracketLevel = 0;
		int argStartIndex = 0;
		
		for(int i = 0; i < argumentTokens.size(); i++) {
			Token token = argumentTokens.get(i);
			
			if(token.type() == TokenType.BRACKET_OPEN) { bracketLevel++; }
			
			if(bracketLevel == 0) {
				if(token.type() == TokenType.COMMA) {
					args.add(parseValue(argumentTokens.subList(argStartIndex, i)));
					argStartIndex = i+1;
				}
			}
			
			if(token.type() == TokenType.BRACKET_CLOSE) { bracketLevel--; }
		}
		
		args.add(parseValue(argumentTokens.subList(argStartIndex, argumentTokens.size())));
		
		return new FunctionCallNode(function, args.toArray(new ValueNode[args.size()]));
	}
}