package org.jpc.engine.jpl;


import org.jpc.term.Term;
import org.jpc.visitor.JpcWriterVisitor;
import org.jpc.visitor.jpl.JpcToJplAdapterVisitor;
import org.jpc.visitor.jpl.JplToJpcAdapterVisitor;
import org.jpc.visitor.jpl.JplWriterVisitor;

/**
 * A utility class for transforming Jpc terms to Jpl and vice-versa
 * @author sergioc
 *
 */
public class JplBridge {

	public static jpl.Term fromJpcToJpl(Term term) {
		JplWriterVisitor jplTermWriter = new JplWriterVisitor();
		term.accept(new JpcToJplAdapterVisitor(jplTermWriter));
		return jplTermWriter.terms().get(0);
	} 
	
	public static Term fromJplToJpc(jpl.Term term) {
		JpcWriterVisitor jpcTermWriter = new JpcWriterVisitor();
		new JplToJpcAdapterVisitor(jpcTermWriter).visit(term);
		return jpcTermWriter.terms().get(0);
	} 

}
