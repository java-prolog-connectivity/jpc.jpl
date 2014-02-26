package org.jpc.jpl;

import static org.jpc.engine.provider.PrologEngineProviderManager.setPrologEngineProvider;

import org.jpc.engine.provider.SimpleEngineProvider;
import org.jpc.util.config.EngineConfigurationManager;
import org.junit.BeforeClass;

public class JplSwiLogtalkEngineTestSuite extends JplLogtalkEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		EngineConfigurationManager engineConfigurationManager = EngineConfigurationManager.createFromFile("jpc_swi_logtalk.settings");
		EngineConfigurationManager.setDefault(engineConfigurationManager);
		setPrologEngineProvider(new SimpleEngineProvider(EngineConfigurationManager.getDefault().getNamedPrologEngine("swi_logtalk")));
	}
}
