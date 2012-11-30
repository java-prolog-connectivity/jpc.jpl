package org.jpc.salt.jpl;

import org.jpc.salt.TermBuilder;
import org.jpc.salt.TermWriter;

public class JplTermWriter extends TermWriter<jpl.Term> {

	@Override
	public void startIntegerTerm(long value) {
		process(new jpl.Integer(value));
	}

	@Override
	public void startFloatTerm(double value) {
		process(new jpl.Float(value));
	}

	@Override
	public void startVariable(String name) {
		process(new jpl.Variable(name));
	}
	
	@Override
	public void startAtom(String name) {
		process(new jpl.Atom(name));
	}

	protected TermBuilder<jpl.Term> createCompoundBuilder() {
		return new JplTermBuilder();
	}
	
}
