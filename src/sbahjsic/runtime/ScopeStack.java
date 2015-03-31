package sbahjsic.runtime;

import java.util.ArrayDeque;
import java.util.Deque;

import sbahjsic.runtime.Scope.ScopeBuilder;

/** A stack of scopes.*/
public final class ScopeStack {
	
	private final Deque<Scope> stack = new ArrayDeque<>();
	
	{
		stack.push(new ScopeBuilder("normal").setIsExecuted(true).build());
	}
	
	public void push(Scope scope) {
		stack.push(scope);
	}
	
	public Scope top() {
		return stack.peek();
	}
	
	public void removeTop() {
		if(stack.size() <= 1) {
			throw new ScopeException("Last scope exited");
		}
		stack.pop();
	}
}