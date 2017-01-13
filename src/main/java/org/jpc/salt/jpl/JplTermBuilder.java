package org.jpc.salt.jpl;

import org.jpc.salt.TermBuilder;

public class JplTermBuilder extends TermBuilder<org.jpl7.Term> {

//	public JplTermBuilder() {
//	}
//	
//	public JplTermBuilder(jpl.Term functor) {
//		super(functor);
//	}
//	
//	public JplTermBuilder(jpl.Term functor, List<jpl.Term> args) {
//		super(functor, args);
//	}
	
	@Override
	public org.jpl7.Term build() {
		org.jpl7.Term builtTerm;
		if(!isCompound())
			builtTerm = getFunctor();
		else {
			if(getFunctor() instanceof org.jpl7.Atom)
				builtTerm = new org.jpl7.Compound(((org.jpl7.Atom)getFunctor()).name(), getArgs().toArray(new org.jpl7.Term[]{}));
			else
				throw new RuntimeException("Invalid functor type");
		}
		return builtTerm;
	}

}
