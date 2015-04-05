package sbahjsic.runtime;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import sbahjsic.runtime.type.SNull;

/** The state of the Sbahjsic runtime.*/
public final class RuntimeContext {
	
	private final Deque<SValue> stack = new ArrayDeque<>();
	
	private final Map<String, SValue> memory = DefaultFunctions.get();
	
	private final ScopeStack scopes = new ScopeStack();
	
	private int line;
	
	/** Peeks the stack.
	 * @return an empty optional if the stack is empty or the top*/
	public Optional<SValue> peek() {
		SValue val = stack.peek();
		return val == null ? Optional.empty() : Optional.of(val);
	}
	
	/** Pushes a value to the stack.
	 * @param value the value to push*/
	public void push(SValue value) {
		stack.push(value);
	}
	
	/** Pops the top value of the stack. Throws an exception for
	 * empty stacks.
	 * @return the popped value*/
	public SValue pop() {
		SValue val = stack.pop();
		return val;
	}
	
	/** Pops the top value of the stack if it exists. Otherwise
	 * returns an empty optional.
	 * @return the popped value or an empty optional*/
	public Optional<SValue> safePop() {
		if(stack.isEmpty()) { return Optional.empty(); }
		return Optional.of(pop());
	}
	
	/** Sets a value in the memory.
	 * @param name the memory address
	 * @param value the value*/
	public void setMemory(String name, SValue value) {
		memory.put(name, value);
	}
	
	/** Returns a value in the memory.
	 * @param name the memory address
	 * @return the value in {@code name}*/
	public SValue getMemory(String name) {
		if(memory.get(name) == null) {
			setMemory(name, SNull.INSTANCE);
		}
		return memory.get(name);
	}
	
	/** Deletes something from the memory.
	 * @param name the item to delete*/
	public void deleteMemory(String name) {
		memory.remove(name);
	}
	
	/** Sets the line current number.
	 * @param num the new line number*/
	public void setLineNumber(int num) {
		line = num;
	}
	
	/** Returns the current line number.
	 * @return the current line number*/
	public int getLineNumber() { return line; }
	
	/** Returns the modifiable {@code ScopeStack} associated with
	 * this {@code RuntimeContext}.
	 * @return the scope stack*/
	public ScopeStack scopeStack() { return scopes; }
	
	/** Iterates over all defined memory values.
	 * @param consumer the consumer that accepts all memory values*/
	public void forMemoryValues(BiConsumer<String, SValue> consumer) {
		memory.entrySet().stream().forEach(entry -> {
			consumer.accept(entry.getKey(), entry.getValue());
		});
	}
}