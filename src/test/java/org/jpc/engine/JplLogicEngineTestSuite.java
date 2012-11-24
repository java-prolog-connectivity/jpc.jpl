package org.jpc.engine;

import org.jpc.LogicEngineTestSuite;
import org.jpc.engine.jpl.DefaultJplConfiguration.DefaultJplYapConfiguration;
import org.jpc.util.ThreadLocalLogicEngine;
import org.junit.BeforeClass;

public class JplLogicEngineTestSuite extends LogicEngineTestSuite {

	@BeforeClass
	public static void setUp() {
		ThreadLocalLogicEngine.setLogicEngine(new DefaultJplYapConfiguration().getEngine());
	}
	
}
