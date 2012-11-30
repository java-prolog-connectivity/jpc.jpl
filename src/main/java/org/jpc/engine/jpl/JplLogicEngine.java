package org.jpc.engine.jpl;

import org.jpc.engine.BootstrapLogicEngine;
import org.jpc.engine.Query;
import org.jpc.term.Term;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JplLogicEngine extends BootstrapLogicEngine {

	private static Logger logger = LoggerFactory.getLogger(JplLogicEngine.class);
	

	@Override
	public Query createQuery(Term term) {
		return new JplQuery(term);
	}

	@Override
	public Term asTerm(String termString) {
		jpl.Term jplTerm = jpl.Util.textToTerm(termString);
		return JplBridge.fromJplToJpc(jplTerm);
	}

	/*
	@Override
	public boolean halt() {
		logger.info("Shutting down the prolog engine ...");
		boolean result = hasSolution(new Atom("halt")); //WARNING: apparently there is a bug in JPL that makes the Java process dye at this point.
		if(result)
			logger.info("The prolog engine has been shut down.");
		else
			logger.warn("Impossible to shut down the prolog engine.");
		return result;
	}
	*/
}
