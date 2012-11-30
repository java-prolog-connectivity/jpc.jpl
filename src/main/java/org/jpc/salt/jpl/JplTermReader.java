package org.jpc.salt.jpl;


import org.jpc.salt.ContentHandler;
import org.jpc.salt.TermReader;

/**
 * a SALT jpl term reader
 * @author sergioc
 *
 */
public class JplTermReader extends TermReader {

	public JplTermReader(ContentHandler contentHandler) {
		super(contentHandler);
	}

	public void stream(jpl.Term term) {
		if(term.isInteger()) {
			jpl.Integer jplInteger = (jpl.Integer) term;
			startIntegerTerm(jplInteger.longValue());
		} else 	if(term.isFloat()) {
			jpl.Float jplFloat = (jpl.Float) term;
			startFloatTerm(jplFloat.doubleValue());
		} else if (term.isVariable()) {
			startVariable(term.name());
		} else if (term.isAtom()) {
			startAtom(term.name());
		} else if(term.isCompound()) {
			startCompound();
			startAtom(term.name());
			for(jpl.Term child : term.args()) {
				stream(child);
			}
			endCompound();
		} else
			throw new RuntimeException("Unrecognized JPL term: " + term);
	}
	
}
