package org.jpc.examples.metro.jpl;


import org.jpc.examples.metro.StressTest;
import org.jpc.examples.metro.model.hlapi.HighLevelApiTestSuite;
import org.jpc.util.config.EngineConfigurationManager;
import org.junit.BeforeClass;

public class StressTestJpl extends StressTest {

	@BeforeClass
    public static void oneTimeSetUp() {
		EngineConfigurationManager engineConfigurationManager = EngineConfigurationManager.createFromFile("jpc_swi_logtalk.settings");
		EngineConfigurationManager.setDefault(engineConfigurationManager);
		HighLevelApiTestSuite.oneTimeSetUp();
		//LowLevelApiTestSuite.oneTimeSetUp();
    }

}
