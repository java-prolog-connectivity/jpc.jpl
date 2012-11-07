package org.jpc.test.engine;


import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import org.jpc.engine.jpl.visitor.JpcToJplAdapterVisitor;
import org.jpc.engine.jpl.visitor.JplTermWriterVisitor;
import org.jpc.engine.jpl.visitor.JplToJpcAdapterVisitor;
import org.jpc.engine.visitor.JpcTermWriterVisitor;
import org.jpc.term.Atom;
import org.jpc.term.Compound;
import org.jpc.term.Term;
import org.jpc.term.Variable;
import org.junit.Test;

public class TermVisitorTest {

	jpl.Term t1Jpl = new jpl.Compound("name", new jpl.Term[] {new jpl.Compound("name2", new jpl.Term[] {new jpl.Variable("A")})});
	Term t1Jpc = new Compound("name", asList(new Compound("name2", asList(new Variable("A")))));
	
	@Test
	public void testTermWriter() {
		JplTermWriterVisitor termWriter = new JplTermWriterVisitor();
		termWriter.visit(t1Jpl);
		assertEquals(t1Jpl, termWriter.terms().get(0));
	}
	
	@Test
	public void testJplToJpc() {
		JpcTermWriterVisitor jpcTermWriter = new JpcTermWriterVisitor();
		JplToJpcAdapterVisitor jplVisitor = new JplToJpcAdapterVisitor(jpcTermWriter);
		jplVisitor.visit(t1Jpl);
		assertEquals(t1Jpc, jpcTermWriter.terms().get(0));
	}
	
	@Test
	public void testJplToJpcToJpl() {
		JplTermWriterVisitor jplTermWriter = new JplTermWriterVisitor();
		JpcToJplAdapterVisitor jpcVisitor = new JpcToJplAdapterVisitor(jplTermWriter);
		JplToJpcAdapterVisitor jplVisitor = new JplToJpcAdapterVisitor(jpcVisitor);
		jplVisitor.visit(t1Jpl);
		assertEquals(t1Jpl, jplTermWriter.terms().get(0));
	}
	
	@Test
	public void testJpcCompoundToJpl() {
		Term termJpc = new Compound("name1", asList(new Atom("name2"), new Variable("var1")));
		jpl.Term termJpl = new jpl.Compound("name1", new jpl.Term[] {new jpl.Atom("name2"), new jpl.Variable("var1")});
		JplTermWriterVisitor jplTermWriter = new JplTermWriterVisitor();
		JpcToJplAdapterVisitor jpcVisitor = new JpcToJplAdapterVisitor(jplTermWriter);
		termJpc.accept(jpcVisitor);
		assertEquals(termJpl, jplTermWriter.terms().get(0));
		
	}

}
