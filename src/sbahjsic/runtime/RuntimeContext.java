package sbahjsic.runtime;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Optional;

import sbahjsic.runtime.type.SNull;

public final class RuntimeContext {
	
	private final Deque<SValue> stack = new ArrayDeque<>();
	
	private final Map<String, SValue> memory = DefaultFunctions.get();
	
	public void push(SValue value) {
		stack.push(value);
	}
	
	public SValue pop() {
		return stack.pop();
	}
	
	public Optional<SValue> safePop() {
		if(stack.isEmpty()) { return Optional.empty(); }
		return Optional.of(pop());
	}
	
	public void setMemory(String name, SValue value) {
		memory.put(name, value);
	}
	
	public SValue getMemory(String name) {
		if(memory.get(name) == null) {
			setMemory(name, SNull.INSTANCE);
		}
		return memory.get(name);
	}
}