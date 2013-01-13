package org.jpc.engine.jpl;

import static java.util.Arrays.asList;

import org.jpc.engine.prolog.BootstrapPrologEngine;
import org.jpc.engine.prolog.PrologEngine;
import org.jpc.query.Query;
import org.jpc.term.Atom;
import org.jpc.term.Compound;
import org.jpc.term.Term;
import org.jpc.term.TermConvertable;
import org.jpc.term.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JplPrologEngine extends BootstrapPrologEngine {

	private static Logger logger = LoggerFactory.getLogger(JplPrologEngine.class);
	

	@Override
	public Query query(TermConvertable termConvertable) {
		return new JplQuery(new PrologEngine(this), termConvertable);
	}

	@Override
	public Term asTerm(String termString) {
		jpl.Term jplTerm = jpl.Util.textToTerm(termString);
		return JplBridge.fromJplToJpc(jplTerm);
	}

	@Override
	public String escape(String s) {
		String escapeVarName = "S";
		Compound goal = new Compound("with_output_to", 
									asList(
										new Compound("atom", 
											asList(new Variable(escapeVarName))),
										new Compound("writeq",
											asList(new Atom(s)))
											));
		Query query = query(goal);
		String escaped = ((Atom)query.oneSolution().get(escapeVarName)).getName();
		return escaped;
	}
	
	/*
	@Override
	public boolean stop() {
		logger.info("Shutting down the JPL prolog engine ...");
		boolean result = hasSolution(new Atom("halt")); //WARNING: the Java process would also dye. Commented out until finding another way to halt the JPL logic engine.
		if(result)
			logger.info("A Jpl prolog engine has been shut down.");
		else
			logger.warn("Impossible to shut down the prolog engine.");
		return result;
	}
	*/
}
