package org.jpc.salt.jpl;

import org.jpc.salt.ContentHandler;
import org.jpc.salt.TermBuilder;
import org.jpc.salt.TermWriter;

public class JplTermWriter extends TermWriter<jpl.Term> {

	@Override
	public ContentHandler startIntegerTerm(long value) {
		process(new jpl.Integer(value));
		return this;
	}

	@Override
	public ContentHandler startFloatTerm(double value) {
		process(new jpl.Float(value));
		return this;
	}

	@Override
	public ContentHandler startVariable(String name) {
		process(new jpl.Variable(name));
		return this;
	}
	
	@Override
	public ContentHandler startAtom(String name) {
		process(new jpl.Atom(name));
		return this;
	}

	protected TermBuilder<jpl.Term> createCompoundBuilder() {
		return new JplTermBuilder();
	}
	
}
