package org.jpc.jpl;

import static org.jpc.engine.provider.PrologEngineProviderManager.setPrologEngineProvider;

import org.jpc.engine.provider.SimpleEngineProvider;
import org.jpc.util.config.EngineConfigurationManager;
import org.junit.BeforeClass;

public class JplYapPrologEngineTestSuite extends JplPrologEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		setPrologEngineProvider(new SimpleEngineProvider(EngineConfigurationManager.getDefault().forAlias("yap")));
	}

}
