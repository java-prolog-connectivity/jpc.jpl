package org.jpc.engine.jpl;


import org.jpc.term.Term;
import org.jpc.util.salt.JpcTermStreamer;
import org.jpc.util.salt.jpl.JplTermReader;
import org.jpc.util.salt.jpl.JplTermStreamer;
import org.jpc.util.termprocessor.JpcTermCollector;
import org.jpc.util.termprocessor.GenericTermCollector;

/**
 * A utility class for transforming Jpc terms to Jpl and vice-versa
 * @author sergioc
 *
 */
public class JplBridge {

	public static org.jpl7.Term fromJpcToJpl(Term term) {
		GenericTermCollector<org.jpl7.Term> collector = new GenericTermCollector<>();
		JplTermStreamer jplTermWriter = new JplTermStreamer(collector);
		term.read(jplTermWriter);
		return collector.getFirst();
	} 
	
	public static Term fromJplToJpc(org.jpl7.Term term) {
		JpcTermCollector collector = new JpcTermCollector();
		JpcTermStreamer jpcTermWriter = new JpcTermStreamer(collector);
		new JplTermReader(term, jpcTermWriter).read();
		return collector.getFirst();
	} 

}
