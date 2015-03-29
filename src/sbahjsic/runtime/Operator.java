package sbahjsic.runtime;


/** Contains an operator. Create instances with the static factory methods.*/
@FunctionalInterface
public interface Operator {
	
	/** Calls this operator with some values.
	 * @param context the runtime context
	 * @param values the arguments
	 * @return the result*/
	public SValue apply(RuntimeContext context, SValue... values);
	
	public static Operator unaryOperator(UnOperator func) {
		return (context, values) -> {
			if(values.length != 1) {
				throw new OperatorCallException("Called with " + values.length + 
						" arguments, expected 1");
			}
			return func.apply(context, values[0]);
		};
	}
	
	public static Operator binaryOperator(BiOperator func) {
		return (context, values) -> {
			if(values.length != 2) {
				throw new OperatorCallException("Called with " + values.length + 
						" arguments, expected 2");
			}
			return func.apply(context, values[0], values[1]);
		};
	}
	
	@FunctionalInterface
	public static interface UnOperator {
		SValue apply(RuntimeContext context, SValue caller);
	}
	
	@FunctionalInterface
	public static interface BiOperator {
		SValue apply(RuntimeContext context, SValue caller, SValue arg);
	}
}