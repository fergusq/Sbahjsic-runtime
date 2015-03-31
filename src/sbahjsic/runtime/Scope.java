package sbahjsic.runtime;

import java.util.ArrayList;
import java.util.List;

import sbahjsic.parser.compiler.InstructionType;

/** A possible Sbahjsic scope.*/
public final class Scope {
	
	private final String name;
	private boolean isExecuted;
	private final List<InstructionType> permittedInstructions;
	
	private Scope(String name, boolean isExecuted, InstructionType[] permitted) {
		this.name = name;
		this.isExecuted = isExecuted;
		permittedInstructions = new ArrayList<>();
		for(InstructionType ins : permitted)
			permittedInstructions.add(ins);
	}
	
	/** Returns whether instructions in this scope are executed.
	 * @return whether instructions in this scope are executed*/
	public boolean isExecuted() {
		return isExecuted;
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
	
	@Override
	public String toString() {
		return name;
	}
	
	public static class ScopeBuilder {
		
		private String name;
		private boolean isExecuted = true;
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
		
		public Scope build() {
			return new Scope(name, isExecuted, permitted);
		}
	}
}