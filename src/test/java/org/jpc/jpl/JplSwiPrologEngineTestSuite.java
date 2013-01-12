package org.jpc.jpl;

import org.jpc.engine.jpl.DefaultJplSwiConfiguration;
import org.jpc.util.ThreadLocalLogicEngine;
import org.junit.BeforeClass;

public class JplSwiPrologEngineTestSuite extends JplPrologEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		ThreadLocalLogicEngine.setLogicEngine(new DefaultJplSwiConfiguration().getEngine());
	}
}
