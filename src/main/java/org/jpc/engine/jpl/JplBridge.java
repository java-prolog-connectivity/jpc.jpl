package org.jpc.engine.jpl;


import org.jpc.term.Term;
import org.jpc.util.salt.JpcTermStreamer;
import org.jpc.util.salt.TermCollector;
import org.jpc.util.salt.jpl.JplTermReader;
import org.jpc.util.salt.jpl.JplTermStreamer;

/**
 * A utility class for transforming Jpc terms to Jpl and vice-versa
 * @author sergioc
 *
 */
public class JplBridge {

	public static org.jpl7.Term fromJpcToJpl(Term term) {
		TermCollector<org.jpl7.Term> collector = new TermCollector();
		JplTermStreamer jplTermWriter = new JplTermStreamer(collector);
		term.read(jplTermWriter);
		return collector.getFirst();
	} 
	
	public static Term fromJplToJpc(org.jpl7.Term term) {
		TermCollector<Term> collector = new TermCollector();
		JpcTermStreamer jpcTermWriter = new JpcTermStreamer(collector);
		new JplTermReader(term, jpcTermWriter).read();
		return collector.getFirst();
	} 

}
