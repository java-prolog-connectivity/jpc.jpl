package org.jpc.jpl;

import org.jpc.engine.jpl.DefaultJplSwiConfiguration;
import org.jpc.util.concurrent.ThreadLocalPrologEngine;
import org.junit.BeforeClass;

public class JplSwiLogtalkEngineTestSuite extends JplLogtalkEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		ThreadLocalPrologEngine.setPrologEngine(new DefaultJplSwiConfiguration().getEngine());
	}
}
