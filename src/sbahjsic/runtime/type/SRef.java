package sbahjsic.runtime.type;

import sbahjsic.core.Main;
import sbahjsic.runtime.Operator.BiOperator;
import sbahjsic.runtime.OperatorCallException;
import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.SValue;

public final class SRef extends AbstractType {
	
	private final static BiOperator ASSIGNMENT = (context, a1, a2) -> {
		context.setMemory(a1.asAddress(), a2);
		return SVoid.INSTANCE;
	};
	
	private final String address;
	final SValue refersTo;
	
	public SRef(String address, SValue val) {
		this.address = address;
		this.refersTo = val;
	}
	
	@Override
	public SValue call(RuntimeContext context, SValue... args) {
		return refersTo.call(context, args);
	}
	
	@Override
	public SValue callOperator(RuntimeContext context, String op, SValue... args) {
		if(op.equals("=")) {
			if(args.length != 1) {
				throw new OperatorCallException("Called with " + args.length + 
						" arguments, expected 1");
			}
			return ASSIGNMENT.apply(context, this, args[0]);
		}
		return refersTo.callOperator(context, op, args);
	}

	@Override
	public String typeName() { return refersTo.typeName(); }

	@Override
	public int asInt() { return refersTo.asInt(); }
	
	@Override
	public long asLong() {
		if(!Main.ARGS.fixLongs()) {
			return refersTo.asInt();
		}
		return refersTo.asLong();
	}

	@Override
	public String asString() { return refersTo.asString(); }
	
	@Override
	public String asAddress() { return address; }

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