package org.jpc.engine;

import org.jpc.engine.TestLogtalkLogicEngine;
import org.jpc.engine.jpl.DefaultJplConfiguration.DefaultJplYapConfiguration;
import org.jpc.util.ThreadLocalLogicEngine;
import org.junit.BeforeClass;

public class TestJplLogtalk extends TestLogtalkLogicEngine {
	
	@BeforeClass
	public static void setUp() {
		ThreadLocalLogicEngine.setLogicEngine(new DefaultJplYapConfiguration().getEngine());
	}
	
}
