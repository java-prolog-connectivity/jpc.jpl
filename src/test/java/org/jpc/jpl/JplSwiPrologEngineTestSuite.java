package org.jpc.jpl;

import org.jpc.engine.jpl.DefaultJplSwiConfiguration;
import org.jpc.engine.prolog.configuration.PrologEngineConfiguration;
import org.jpc.util.concurrent.ThreadLocalPrologEngine;
import org.junit.BeforeClass;

public class JplSwiPrologEngineTestSuite extends JplPrologEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		PrologEngineConfiguration prologEngineConfiguration = new DefaultJplSwiConfiguration();
		prologEngineConfiguration.setLogtalkRequired(false);
		ThreadLocalPrologEngine.setPrologEngine(prologEngineConfiguration.createPrologEngine());
	}
}
