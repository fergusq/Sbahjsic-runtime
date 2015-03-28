package sbahjsic.runtime.type;

import sbahjsic.runtime.Operator.BiOperator;
import sbahjsic.runtime.OperatorCallException;
import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.SValue;

public final class SRef extends AbstractType {
	
	private final static BiOperator ASSIGNMENT = (context, a1, a2) -> {
		context.setMemory(((SRef) a1).address, a2);
		return SNull.INSTANCE;
	};
	
	private final String address;
	private final SValue refersTo;
	
	public SRef(String address, SValue val) {
		this.address = address;
		this.refersTo = val;
	}
	
	@Override
	public SValue callOperator(RuntimeContext context, String op, SValue... args) {
		if(op.equals("=")) {
			if(args.length != 1) {
				throw new OperatorCallException("Called with " + args.length + 
						" arguments, expected 1");
			}
			ASSIGNMENT.apply(context, this, args[0]);
			return SNull.INSTANCE;
		}
		return refersTo.callOperator(context, op, args);
	}

	@Override
	public String typeName() { return "ref"; }

	@Override
	public int asInt() { return refersTo.asInt(); }

	@Override
	public String asString() { return refersTo.asString(); }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SRef)) {
			return false;
		}
		SRef other = (SRef) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		return true;
	}
	
}