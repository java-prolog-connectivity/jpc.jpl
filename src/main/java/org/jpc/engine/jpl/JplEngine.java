package org.jpc.engine.jpl;

import static org.jpc.engine.prolog.ThreadModel.SINGLE_THREADED;

import org.jpc.Jpc;
import org.jpc.engine.prolog.AbstractPrologEngine;
import org.jpc.engine.prolog.ThreadModel;
import org.jpc.error.PrologParsingException;
import org.jpc.error.SyntaxError;
import org.jpc.query.Query;
import org.jpc.term.Term;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JplEngine extends AbstractPrologEngine {

	private static final Logger logger = LoggerFactory.getLogger(JplEngine.class);

	private final ThreadModel threadModel; 
	
	JplEngine(ThreadModel threadModel) {
		this.threadModel = threadModel;
	}
	
	@Override
	public void close() {
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
	public boolean isCloseable() {
		return false;
	}
	
	@Override
	public ThreadModel threadModel() {
		return threadModel;
	}
	
	@Override
	public Term asTerm(String termString, Jpc context) {
		jpl.Term jplTerm = null;
		try {
			jplTerm = jpl.Util.textToTerm(termString);
		} catch(jpl.PrologException jplException) {
			RuntimeException problem = context.fromTerm(JplBridge.fromJplToJpc(jplException.term()));
			if(problem instanceof SyntaxError)
				problem = new PrologParsingException(termString, problem);
			throw problem;
		}
		return JplBridge.fromJplToJpc(jplTerm);
	}
	
	@Override
	public Query basicQuery(Term goal, boolean errorHandledQuery, Jpc context) {
		if(threadModel.equals(SINGLE_THREADED))
			return new SingleThreadedJplQuery(this, goal, errorHandledQuery, context);
		else
			return new MultiThreadedJplQuery(this, goal, errorHandledQuery, context);
	}

}
