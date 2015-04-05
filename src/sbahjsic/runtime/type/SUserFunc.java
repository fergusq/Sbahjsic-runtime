package sbahjsic.runtime.type;

import java.util.Arrays;
import java.util.Optional;

import sbahjsic.parser.compiler.Instruction;
import sbahjsic.runtime.ExecutionEnvironment;
import sbahjsic.runtime.RuntimeContext;
import sbahjsic.runtime.SValue;
import sbahjsic.runtime.Scope;

/** Represents an user-defined function.*/
public final class SUserFunc extends AbstractType {
	
	private final String[] argNames;
	private final Instruction[] body;
	private final String name;
	
	public SUserFunc(String[] args, Instruction[] body, String name) {
		this.argNames = args;
		this.body = body;
		this.name = name;
	}
	
	@Override
	public SValue call(RuntimeContext context, SValue... arguments) {
		SFunc.requireArguments(argNames.length, arguments.length);
		
		for(int i = 0; i < argNames.length; i++) {
			context.setMemory(argNames[i], arguments[i]);
		}
		
		Scope oldTop = context.scopeStack().top();
		
		new ExecutionEnvironment(context)
				.setRunCode(true)
				.execute(name, body);
		Optional<SValue> returnValue = context.peek();
		
		while(context.scopeStack().top() != oldTop) {
			context.scopeStack().removeTop();
		}
		
		return returnValue.isPresent() ? returnValue.get() : SVoid.INSTANCE;
	}

	@Override
	public String typeName() {
		return "func";
	}

	@Override
	public int asInt() {
		return -1;
	}

	@Override
	public String asString() {
		return "func@" + System.identityHashCode(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(argNames);
		result = prime * result + Arrays.hashCode(body);
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
		if (!(obj instanceof SUserFunc)) {
			return false;
		}
		SUserFunc other = (SUserFunc) obj;
		if (!Arrays.equals(argNames, other.argNames)) {
			return false;
		}
		if (!Arrays.equals(body, other.body)) {
			return false;
		}
		return true;
	}
}