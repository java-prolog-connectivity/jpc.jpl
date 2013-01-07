package org.jpc.engine.jpl;


import org.jpc.salt.JpcTermWriter;
import org.jpc.salt.jpl.JplTermReader;
import org.jpc.salt.jpl.JplTermWriter;
import org.jpc.term.Term;
import org.jpc.term.TermConvertable;

/**
 * A utility class for transforming Jpc terms to Jpl and vice-versa
 * @author sergioc
 *
 */
public class JplBridge {

	public static jpl.Term fromJpcToJpl(TermConvertable termConvertable) {
		JplTermWriter jplTermWriter = new JplTermWriter();
		termConvertable.asTerm().read(jplTermWriter);
		return jplTermWriter.getTerms().get(0);
	} 
	
	public static Term fromJplToJpc(jpl.Term term) {
		JpcTermWriter jpcTermWriter = new JpcTermWriter();
		new JplTermReader(term, jpcTermWriter).read();
		return jpcTermWriter.getTerms().get(0);
	} 

}
