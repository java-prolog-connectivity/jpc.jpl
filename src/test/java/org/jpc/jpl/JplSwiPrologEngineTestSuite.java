package org.jpc.jpl;

import org.jpc.engine.jpl.DefaultJplSwiConfiguration;
import org.jpc.util.concurrent.ThreadLocalPrologEngine;
import org.junit.BeforeClass;

public class JplSwiPrologEngineTestSuite extends JplPrologEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		ThreadLocalPrologEngine.setPrologEngine(new DefaultJplSwiConfiguration().getEngine());
	}
}
