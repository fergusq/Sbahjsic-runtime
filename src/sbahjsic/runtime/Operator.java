package sbahjsic.runtime;


/** Contains an operator. Create instances with the static factory methods.*/
@FunctionalInterface
public interface Operator {
	
	/** Calls this operator with some values.
	 * @param values the arguments
	 * @return the result*/
	public SValue apply(SValue... values);
	
	public static Operator unaryOperator(UnOperator func) {
		return values -> {
			if(values.length != 1) {
				throw new OperatorCallException("Called with " + values.length + 
						" arguments, expected 1");
			}
			return func.apply(values[0]);
		};
	}
	
	public static Operator binaryOperator(BiOperator func) {
		return values -> {
			if(values.length != 2) {
				throw new OperatorCallException("Called with " + values.length + 
						" arguments, expected 2");
			}
			return func.apply(values[0], values[1]);
		};
	}
	
	@FunctionalInterface
	public interface UnOperator {
		SValue apply(SValue caller);
	}
	
	@FunctionalInterface
	public interface BiOperator {
		SValue apply(SValue caller, SValue arg);
	}
}