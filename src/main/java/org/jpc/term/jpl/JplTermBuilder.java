package org.jpc.term.jpl;

import java.util.List;
import org.jpc.util.TermBuilder;

public class JplTermBuilder extends TermBuilder<jpl.Term> {

	public JplTermBuilder() {
	}
	
	public JplTermBuilder(Object functor) {
		super(functor);
	}
	
	public JplTermBuilder(String functor, List<jpl.Term> args) {
		super(functor, args);
	}
	
	@Override
	public jpl.Term asTerm() {
		jpl.Term builtTerm;
		if(arity() == 0)
			builtTerm = (jpl.Term) getFunctor();
		else {
			if(getFunctor() instanceof String)
				builtTerm = new jpl.Compound((String)getFunctor(), getArgs().toArray(new jpl.Term[]{}));
			else if(getFunctor() instanceof jpl.Atom)
				builtTerm = new jpl.Compound(((jpl.Atom)getFunctor()).name(), getArgs().toArray(new jpl.Term[]{}));
			else
				throw new RuntimeException("Invalid functor type");
		}
		return builtTerm;
	}
}
