package org.jpc.engine.jpl;

import static java.util.Arrays.asList;

import org.jpc.Jpc;
import org.jpc.engine.prolog.AbstractPrologEngine;
import org.jpc.query.Query;
import org.jpc.term.Atom;
import org.jpc.term.Compound;
import org.jpc.term.Term;
import org.jpc.term.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JplPrologEngine extends AbstractPrologEngine {

	private static Logger logger = LoggerFactory.getLogger(JplPrologEngine.class);
	
	@Override
	public boolean interrupt() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean shutdown() {
		throw new UnsupportedOperationException();
//		logger.info("Shutting down the JPL prolog engine ...");
//		boolean result = query(new Atom("halt")).hasSolution(); //WARNING: the Java process would also dye. Commented out until finding another way to halt the JPL logic engine.
//		if(result)
//			logger.info("A Jpl prolog engine has been shut down.");
//		else
//			logger.warn("Impossible to shut down the prolog engine.");
//		return result;
	}
	
	@Override
	public Term asTerm(String termString) {
		jpl.Term jplTerm = jpl.Util.textToTerm(termString);
		return JplBridge.fromJplToJpc(jplTerm);
	}
	
	@Override
	public Query basicQuery(Term term, Jpc context) {
		return new JplQuery(this, term, context);
	}

}
