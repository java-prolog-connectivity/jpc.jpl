package org.jpc.test.engine;

import static junit.framework.Assert.assertTrue;

import java.util.List;

import org.jpc.term.Atom;
import org.jpc.term.Query;
import org.jpc.term.Term;
import org.junit.Test;
import static org.jpc.LogicEngineProvider.logicEngine;
/**
 * This class test some Logtalk functionality (to be completed)
 * @author scastro
 *
 */
public class TestLogtalk  {

//	@Test
//	public void testLoadingLogtalk() {
//		LogicEngine.getDefault().loadLogtalk();
//	}

	
	@Test
	public void t() {
		Query query = logicEngine.createQuery("Op='::', current_op(_, Type, Op), atom_chars(Type, Chars), Chars=[_, f, _]");
		assertTrue(query.hasSolution());
		new Thread() {
			@Override
			public void run() {
				Atom atomOp = new Atom("::");
				//Query query = new Query("Op='?', current_op(_, Type, Op), atom_chars(Type, Chars), Chars=[_, f, _]", new Term[] {atomOp});
				//assertTrue( query.hasSolution() );
			}
			
		}.start();
	}
	
	/**
	 * verify that the Logtalk operators have been defined in the logic engine
	 */
	
	@Test
	public void testLogtalkOperators() {
		assertTrue(logicEngine.isBinaryOperator("::"));
		assertTrue(logicEngine.isUnaryOperator("::"));
	}

	@Test
	public void testCurrentObjects() {
		List<Term> currentObjects = logicEngine.currentLogtalkObjects();
		assertTrue(currentObjects.contains(new Atom("logtalk")));
	}
	
}
