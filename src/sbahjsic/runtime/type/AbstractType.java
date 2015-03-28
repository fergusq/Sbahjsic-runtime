package sbahjsic.runtime.type;

import java.util.HashMap;
import java.util.Map;

import sbahjsic.runtime.Operator;
import sbahjsic.runtime.Operator.BiOperator;
import sbahjsic.runtime.Operator.UnOperator;
import sbahjsic.runtime.OperatorCallException;
import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.SValue;

abstract class AbstractType implements SValue {
	
	private final static Map<Class<? extends SValue>, Map<String, Operator>> OP_MAP = new HashMap<>();
	
	@Override
	public SValue callOperator(RuntimeContext context, String op, SValue... args) {
		Operator operator = operatorMap().get(op);
		
		if(operator == null) {
			throw new OperatorCallException("Unknown operator " + op + " on type " + typeName());
		}
		
		SValue[] newArgs = new SValue[args.length + 1];
		newArgs[0] = this;
		System.arraycopy(args, 0, newArgs, 1, args.length);
		
		return operator.apply(context, newArgs);
	}
	
	public final Map<String, Operator> operatorMap() {
		ensureMapExistance();
		return OP_MAP.get(this.getClass());
	}
	
	private void ensureMapExistance() {
		if(!OP_MAP.containsKey(this.getClass())) {
			OP_MAP.put(this.getClass(), new HashMap<>());
		}
	}
	
	protected void registerUnOperator(String string, UnOperator op) {
		ensureMapExistance();
		OP_MAP.get(this.getClass()).put(string, Operator.unaryOperator(op));
	}
	
	protected void registerBiOperator(String string, BiOperator op) {
		ensureMapExistance();
		OP_MAP.get(this.getClass()).put(string, Operator.binaryOperator(op));
	}
	
	@Override
	public String toString() {
		return asString();
	}
	
	@Override
	public abstract boolean equals(Object o);

	@Override
	public abstract int hashCode();
}