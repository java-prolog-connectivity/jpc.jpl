package org.jpc.util.salt.jpl;


import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import org.jpc.term.Atom;
import org.jpc.term.Compound;
import org.jpc.term.Float;
import org.jpc.term.Integer;
import org.jpc.term.Term;
import org.jpc.term.Var;
import org.jpc.util.salt.JpcTermStreamer;
import org.jpc.util.salt.TermCollector;
import org.junit.Test;

public class JplTransformationTest {

	org.jpl7.Term t1Jpl = new org.jpl7.Compound("id", new org.jpl7.Term[] {new org.jpl7.Compound("name2", new org.jpl7.Term[] {new org.jpl7.Atom("atom1"), new org.jpl7.Integer(-10), new org.jpl7.Float(10.5), new org.jpl7.Variable("A"), new org.jpl7.Variable("_A")})});
	Term t1Jpc = new Compound("id", asList(new Compound("name2", asList(new Atom("atom1"), new Integer(-10), new Float(10.5), new Var("A"), new Var("_A")))));
	
	@Test
	public void testJplToJpl() {
		TermCollector<org.jpl7.Term> collector = new TermCollector();
		JplTermStreamer termWriter = new JplTermStreamer(collector);
		new JplTermReader(t1Jpl, termWriter).read();
		assertEquals(t1Jpl, collector.getFirst());
	}
	
	@Test
	public void testJplToJpc() {
		TermCollector<Term> collector = new TermCollector();
		JpcTermStreamer jpcTermWriter = new JpcTermStreamer(collector);
		new JplTermReader(t1Jpl, jpcTermWriter).read();
		assertEquals(t1Jpc, collector.getFirst());
	}
	
	@Test
	public void testJpcToJpl() {
		TermCollector<org.jpl7.Term> collector = new TermCollector();
		JplTermStreamer jplTermWriter = new JplTermStreamer(collector);
		t1Jpc.read(jplTermWriter);
		assertEquals(t1Jpl, collector.getFirst());
	}
	
}
