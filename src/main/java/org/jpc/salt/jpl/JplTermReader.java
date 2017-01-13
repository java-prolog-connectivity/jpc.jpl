package org.jpc.salt.jpl;


import org.jpc.salt.TermContentHandler;
import org.jpc.salt.TermReader;

/**
 * a SALT jpl term reader
 * @author sergioc
 *
 */
public class JplTermReader extends TermReader {

	private org.jpl7.Term jplTerm;
	
	public JplTermReader(org.jpl7.Term jplTerm, TermContentHandler contentHandler) {
		super(contentHandler);
		this.jplTerm = jplTerm;
	}

	@Override
	public void read() {
		read(jplTerm);
	}
	
	private void read(org.jpl7.Term term) {
		if(term.isInteger()) {
			org.jpl7.Integer jplInteger = (org.jpl7.Integer) term;
			getContentHandler().startIntegerTerm(jplInteger.longValue());
		} else 	if(term.isFloat()) {
			org.jpl7.Float jplFloat = (org.jpl7.Float) term;
			getContentHandler().startFloatTerm(jplFloat.doubleValue());
		} else if (term.isVariable()) {
			getContentHandler().startVariable(term.name());
		} else if (term.isAtom()) {
			getContentHandler().startAtom(term.name());
		} else if(term.isCompound()) {
			getContentHandler().startCompound();
			getContentHandler().startAtom(term.name());
			for(org.jpl7.Term child : term.args()) {
				read(child);
			}
			getContentHandler().endCompound();
		} else
			throw new RuntimeException("Unrecognized JPL term: " + term);
	}
	
}
