package org.jpc.salt.jpl;

import java.util.List;

import org.jpc.salt.TermBuilder;

public class JplTermBuilder extends TermBuilder<jpl.Term> {

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
	public jpl.Term build() {
		jpl.Term builtTerm;
		if(!isCompound())
			builtTerm = getFunctor();
		else {
			if(getFunctor() instanceof jpl.Atom)
				builtTerm = new jpl.Compound(((jpl.Atom)getFunctor()).name(), getArgs().toArray(new jpl.Term[]{}));
			else
				throw new RuntimeException("Invalid functor type");
		}
		return builtTerm;
	}

}
