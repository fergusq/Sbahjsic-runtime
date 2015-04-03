package sbahjsic.runtime;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;

import sbahjsic.runtime.Scope.ScopeBuilder;

/** A stack of scopes.*/
public final class ScopeStack {
	
	private final List<Consumer<Scope>> newListeners = new ArrayList<>();
	private final List<Consumer<Scope>> endListeners = new ArrayList<>();
	
	private final Deque<Scope> stack = new ArrayDeque<>();
	
	{
		stack.push(new ScopeBuilder("normal").setIsExecuted(true).build());
	}
	
	public void push(Scope scope) {
		stack.push(scope);
		for(Consumer<Scope> consumer : newListeners) {
			consumer.accept(scope);
		}
	}
	
	public Scope top() {
		return stack.peek();
	}
	
	public void removeTop() {
		if(stack.size() <= 1) {
			throw new ScopeException("Last scope exited");
		}
		Scope scope = stack.pop();
		for(Consumer<Scope> consumer : endListeners) {
			consumer.accept(scope);
		}
	}
	
	public boolean executeInstructions() {
		boolean negativeFound = false;
		for(Scope scope : stack) {
			if(!scope.isExecuted()) {
				negativeFound = true;
			}
		}
		return !negativeFound;
	}
	
	public void addNewScopeListener(Consumer<Scope> listener) {
		newListeners.add(listener);
	}
	
	public void removeNewScopeListener(Consumer<Scope> listener) {
		newListeners.remove(listener);
	}
	
	public void addScopeEndListener(Consumer<Scope> listener) {
		endListeners.add(listener);
	}
	
	public void removeScopeEndListener(Consumer<Scope> listener) {
		endListeners.remove(listener);
	}
}