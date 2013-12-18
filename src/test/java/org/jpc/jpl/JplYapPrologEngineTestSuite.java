package org.jpc.jpl;

import static org.jpc.engine.provider.PrologEngineProviderManager.setPrologEngineProvider;

import org.jpc.engine.jpl.JplYapDriver;
import org.jpc.engine.prolog.driver.AbstractPrologEngineDriver;
import org.jpc.engine.provider.SimpleEngineProvider;
import org.junit.BeforeClass;

public class JplYapPrologEngineTestSuite extends JplPrologEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		AbstractPrologEngineDriver prologEngineConfiguration = new JplYapDriver();
		setPrologEngineProvider(new SimpleEngineProvider(prologEngineConfiguration.createPrologEngine()));
	}

}
