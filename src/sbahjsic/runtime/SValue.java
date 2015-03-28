package sbahjsic.runtime;

/** A value in the memory.*/
public interface SValue {
	
	/** Calls an operator on this value with some arguments.
	 * @param op the operator
	 * @param args the arguments
	 * @return the result of calling the operator with the arguments*/
	public SValue callOperator(String op, SValue... args);
	
	/** Calls this value with some arguments.
	 * @param args the arguments
	 * @return the result of the call*/
	default public SValue call(SValue... args) {
		throw new CallException("Cannot call type " + typeName());
	}
	
	/** Returns the name of the type represented by this value.
	 * @return the name of this value's type*/
	public String typeName();
	
	/** Returns this value's integer representation.
	 * @return the integer representation of this value*/
	public int asInt();
	
	/** Returns this value's string representation.
	 * @return the string representation of this value*/
	public String asString();
	
	/** Returns this value's float representation.
	 * @return the float representation of this value*/
	default public float asFloat() {
		return asInt();
	}
	
	/** Returns this value's double representation.
	 * @return the double representation of this value*/
	default public double asDouble() {
		return asFloat();
	}
	
	/** Returns this value's long representation.
	 * @return the long representation of this value*/
	default public long asLong() {
		return asInt();
	}
	
	/** Returns this value's boolean representation.
	 * @return the boolean representation of this value*/
	default public boolean asBool() {
		return asInt() == 0 ? false : true;
	}
}