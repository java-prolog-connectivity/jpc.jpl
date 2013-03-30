package org.jpc.engine.jpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.jpc.Jpc;
import org.jpc.query.PrologQuery;
import org.jpc.term.Term;

/**
 * An adapter to a JPL query
 * @author sergioc
 *
 */
public class JplQuery extends PrologQuery {

	private jpl.Query jplQuery;
	
	public JplQuery(JplPrologEngine prologEngine, Term goal, boolean errorHandledQuery, Jpc context) {
		super(prologEngine, goal, errorHandledQuery, context);
		jpl.Term jplGoal = JplBridge.fromJpcToJpl(getInstrumentedGoal());
		jplQuery = new jpl.Query(jplGoal);
	}


	@Override
	protected void basicAbort() {
		throw new UnsupportedOperationException();
		//jplQuery.abort(); //this method exists but does not work
	}

	@Override
	protected void basicClose() {
		jplQuery.close();
	}

	@Override
	public Map<String, Term> basicNext() {
		if(jplQuery.hasMoreSolutions()) {
			Map<String, Term> nextSolution = new HashMap<>();
			Map<String, jpl.Term> jplSolution = jplQuery.nextSolution();
			for(Entry<String, jpl.Term> jplEntry : jplSolution.entrySet()) {
				String varName = jplEntry.getKey();
				Term term = JplBridge.fromJplToJpc(jplEntry.getValue());
				nextSolution.put(varName, term);
			}
			return nextSolution;
		} else {
			throw new NoSuchElementException();
		}
	}

}
