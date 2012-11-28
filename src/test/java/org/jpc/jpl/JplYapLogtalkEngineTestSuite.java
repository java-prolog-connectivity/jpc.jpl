package org.jpc.jpl;

import org.jpc.engine.jpl.DefaultJplConfiguration.DefaultJplYapConfiguration;
import org.jpc.util.ThreadLocalLogicEngine;
import org.junit.BeforeClass;

public class JplYapLogtalkEngineTestSuite extends JplLogtalkEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		ThreadLocalLogicEngine.setLogicEngine(new DefaultJplYapConfiguration().getEngine());
	}
}
