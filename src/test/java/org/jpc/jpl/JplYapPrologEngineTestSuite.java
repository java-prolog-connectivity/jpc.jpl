package org.jpc.jpl;

import org.jpc.engine.jpl.DefaultJplConfiguration.DefaultJplYapConfiguration;
import org.jpc.util.ThreadLocalLogicEngine;
import org.junit.BeforeClass;

public class JplYapPrologEngineTestSuite extends JplPrologEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		ThreadLocalLogicEngine.setLogicEngine(new DefaultJplYapConfiguration().getEngine());
	}
}
