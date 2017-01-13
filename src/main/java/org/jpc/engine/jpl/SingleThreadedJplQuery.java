package org.jpc.engine.jpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.jpc.Jpc;
import org.jpc.query.PrologQuery;
import org.jpc.query.Solution;
import org.jpc.term.Term;

/**
 *   This class wraps a JPL Query and has the same limitations regarding JPL multithreading support.
 *   For example, at the moment, JPL requires that calls to query methods hasMoreSolutions() and nextSolution() occur in the same thread.
 *   Then, JPC query methods hasNext() and next() (wrapping the previous JPL methods) also share this limitation.
 *   Also, a JPL query should not be opened in the same thread where another JPL query is already open. The same is true for instances of this class.
 *   
 *   
 * @author sergioc
 *
 */
public class SingleThreadedJplQuery extends PrologQuery {

    private org.jpl7.Term jplGoal;
    private org.jpl7.Query jplQuery;

    public SingleThreadedJplQuery(JplEngine prologEngine, Term goal, boolean errorHandledQuery, Jpc context) {
        super(prologEngine, goal, errorHandledQuery, context);
        jplGoal = JplBridge.fromJpcToJpl(getInstrumentedGoal());
    }
 
    @Override
    public boolean isAbortable() {
        return false;
    }
     
    @Override
    protected void basicAbort() {
        throw new UnsupportedOperationException();
        //jplQuery.abort(); //this method exists but does not work
    }
	
    public void resetJplQuery() {
    	jplQuery = null;
    }
    
    @Override
    protected void basicClose() {
    	if(jplQuery != null) {
    		jplQuery.close();
    	}
    }
 
    @Override
    protected Solution basicNext() {
        if(jplQuery == null) {
            jplQuery = new org.jpl7.Query(jplGoal);
        }
        Solution querySolution = null;
        try {
        	if(jplQuery.hasMoreSolutions()) {
                Map<String, Term> nextSolution = new HashMap<>();
                Map<String, org.jpl7.Term> jplSolution = jplQuery.nextSolution();
                for(Entry<String, org.jpl7.Term> jplEntry : jplSolution.entrySet()) {
                    String varName = jplEntry.getKey();
                    Term term = JplBridge.fromJplToJpc(jplEntry.getValue());
                    nextSolution.put(varName, term);
                }
                querySolution = new Solution(nextSolution, getPrologEngine(), getJpcContext());
            } else
            	jplQuery.close();
        } catch(Exception e) {
        	jplQuery.close();
            throw e;
        }
        if(querySolution != null)
            return querySolution;
        else
            throw new NoSuchElementException();
    }
     
}