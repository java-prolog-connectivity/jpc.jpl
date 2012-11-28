package org.jpc.term.jpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jpc.engine.jpl.JplBridge;
import org.jpc.term.Query;
import org.jpc.term.Term;

/**
 * An adapter to a JPL query
 * @author sergioc
 *
 */
public class JplQuery extends Query {

	private jpl.Query jplQuery;
	
	public JplQuery(Term goal) {
		super(goal);
		jpl.Term jplGoal = JplBridge.fromJpcToJpl(goal);
		jplQuery = new jpl.Query(jplGoal);
	}

	@Override
	public boolean isOpen() {
		return jplQuery.isOpen();
	}

	@Override
	public void abort() {
		jplQuery.abort();
	}

	@Override
	public void close() {
		jplQuery.close();
	}

	@Override
	public boolean hasNext() {
		return jplQuery.hasMoreSolutions();
	}

	@Override
	public Map<String, Term> next() {
		Map<String, Term> nextSolution = new HashMap<>();
		Map<String, jpl.Term> jplSolution = jplQuery.nextSolution();
		if(jplSolution != null) {
			for(Entry<String, jpl.Term> jplEntry : jplSolution.entrySet()) {
				String varName = jplEntry.getKey();
				Term term = JplBridge.fromJplToJpc(jplEntry.getValue());
				nextSolution.put(varName, term);
			}
		}
		return nextSolution;
	}
}
