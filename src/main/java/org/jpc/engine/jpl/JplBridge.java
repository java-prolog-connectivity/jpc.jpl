package org.jpc.engine.jpl;

import org.jpc.engine.jpl.visitor.JpcToJplAdapterVisitor;
import org.jpc.engine.jpl.visitor.JplTermWriterVisitor;
import org.jpc.engine.jpl.visitor.JplToJpcAdapterVisitor;
import org.jpc.engine.visitor.JpcTermWriterVisitor;
import org.jpc.term.Term;

public class JplBridge {

	public static jpl.Term fromJpcToJpl(Term term) {
		JplTermWriterVisitor jplTermWriter = new JplTermWriterVisitor();
		term.accept(new JpcToJplAdapterVisitor(jplTermWriter));
		return jplTermWriter.terms().get(0);
	} 
	
	public static Term fromJplToJpc(jpl.Term term) {
		JpcTermWriterVisitor jpcTermWriter = new JpcTermWriterVisitor();
		new JplToJpcAdapterVisitor(jpcTermWriter).visit(term);
		return jpcTermWriter.terms().get(0);
	} 

}
