package sbahjsic.runtime;

import java.util.ArrayList;
import java.util.List;

import sbahjsic.parser.compiler.InstructionType;

/** A possible Sbahjsic scope.*/
public final class Scope {
	
	private final String name;
	private boolean isExecuted;
	private boolean loops;
	private final int jumpsBack;
	private final List<InstructionType> permittedInstructions;
	private int start = -1;
	
	private Scope(String name, boolean isExecuted, boolean loops, int jumpsBack, InstructionType[] permitted) {
		this.name = name;
		this.isExecuted = isExecuted;
		this.loops = loops;
		this.jumpsBack = jumpsBack;
		permittedInstructions = new ArrayList<>();
		for(InstructionType ins : permitted)
			permittedInstructions.add(ins);
	}
	
	/** Returns whether instructions in this scope are executed.
	 * @return whether instructions in this scope are executed*/
	public boolean isExecuted() {
		return isExecuted;
	}
	
	/** Returns whether this scope loops.
	 * @return whether this scope loops*/
	public boolean loops() {
		return isExecuted() && loops;
	}
	
	/** Switches whether instructions in this scope are executed.*/
	public void switchExecution() {
		isExecuted = !isExecuted;
	}
	
	/** Returns instructions executed regardless of {@code isExecuted()}.
	 * @return instructions executed always*/
	public List<InstructionType> getPermittedInstructions() {
		return permittedInstructions;
	}
	
	/** Sets the start of this scope.
	 * @param start the start of this scope*/
	public void setStart(int start) {
		this.start = start;
	}
	
	/** Returns the start of this scope with possible
	 * adjustments.
	 * @return the start of this scope*/
	public int getStart() { return start; }
	
	public int getJumpsBack() { return jumpsBack; }
	
	@Override
	public String toString() {
		return name;
	}
	
	public static class ScopeBuilder {
		
		private String name;
		private boolean isExecuted = true;
		private boolean loops = false;
		private int jumpsBack = 0;
		private InstructionType[] permitted = new InstructionType[0];
		
		public ScopeBuilder(String name) {
			this.name = name;
		}
		
		public ScopeBuilder setIsExecuted(boolean isExecuted) {
			this.isExecuted = isExecuted;
			return this;
		}
		
		public ScopeBuilder setPermittedInstructions(InstructionType... permitted) {
			this.permitted = permitted;
			return this;
		}
		
		public ScopeBuilder setLoop(boolean loops, int jumpsBack) {
			this.loops = loops;
			this.jumpsBack = jumpsBack;
			return this;
		}
		
		public Scope build() {
			return new Scope(name, isExecuted, loops, jumpsBack, permitted);
		}
	}
}