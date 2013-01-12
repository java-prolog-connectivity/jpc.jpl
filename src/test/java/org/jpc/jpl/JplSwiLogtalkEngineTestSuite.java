package org.jpc.jpl;

import org.jpc.engine.jpl.DefaultJplSwiConfiguration;
import org.jpc.util.ThreadLocalLogicEngine;
import org.junit.BeforeClass;

public class JplSwiLogtalkEngineTestSuite extends JplLogtalkEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		ThreadLocalLogicEngine.setLogicEngine(new DefaultJplSwiConfiguration().getEngine());
	}
}
