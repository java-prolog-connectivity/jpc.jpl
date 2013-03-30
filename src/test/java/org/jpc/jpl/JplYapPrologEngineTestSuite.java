package org.jpc.jpl;

import org.jpc.engine.jpl.DefaultJplYapConfiguration;
import org.jpc.engine.prolog.configuration.PrologEngineConfiguration;
import org.jpc.util.concurrent.ThreadLocalPrologEngine;
import org.junit.BeforeClass;

public class JplYapPrologEngineTestSuite extends JplPrologEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		PrologEngineConfiguration prologEngineConfiguration = new DefaultJplYapConfiguration();
		prologEngineConfiguration.setLogtalkRequired(false);
		ThreadLocalPrologEngine.setPrologEngine(prologEngineConfiguration.createPrologEngine());
	}

}
