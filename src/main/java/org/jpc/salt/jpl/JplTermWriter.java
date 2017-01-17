package org.jpc.salt.jpl;

import org.jpc.engine.prolog.PrologConstants;
import org.jpc.salt.TermBuilder;
import org.jpc.salt.TermContentHandler;
import org.jpc.salt.TermWriter;
import org.jpl7.JPL;

public class JplTermWriter extends TermWriter<org.jpl7.Term> {

	@Override
	public TermContentHandler startIntegerTerm(long value) {
		process(new org.jpl7.Integer(value));
		return this;
	}

	@Override
	public TermContentHandler startFloatTerm(double value) {
		process(new org.jpl7.Float(value));
		return this;
	}

	@Override
	public TermContentHandler startVariable(String name) {
		process(new org.jpl7.Variable(name));
		return this;
	}
	
	@Override
	public TermContentHandler startAtom(String name) {
		if (name.equals(PrologConstants.CONS_FUNCTOR)) {
			process(new org.jpl7.Atom(JPL.LIST_PAIR));
		} else if (name.equals(PrologConstants.NIL_SYMBOL)) {
			process(JPL.LIST_NIL);
		} else {
			process(new org.jpl7.Atom(name));
		}
		return this;
	}

	@Override
	public TermContentHandler startJRef(Object ref) {
		throw new UnsupportedOperationException();
	}

	protected TermBuilder<org.jpl7.Term> createCompoundBuilder() {
		return new JplTermBuilder();
	}
	
}
