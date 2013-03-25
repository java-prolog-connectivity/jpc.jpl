package org.jpc.jpl;

import org.jpc.engine.jpl.DefaultJplYapConfiguration;
import org.jpc.util.concurrent.ThreadLocalPrologEngine;
import org.junit.BeforeClass;

public class JplYapLogtalkEngineTestSuite extends JplLogtalkEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		ThreadLocalPrologEngine.setPrologEngine(new DefaultJplYapConfiguration().createPrologEngine());
	}
}
