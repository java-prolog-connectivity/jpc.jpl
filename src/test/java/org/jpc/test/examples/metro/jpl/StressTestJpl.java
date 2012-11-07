package org.jpc.test.examples.metro.jpl;

import static junit.framework.Assert.assertTrue;

import org.jpc.LogicEngineProvider;
import org.jpc.engine.jpl.DefaultJplConfiguration;
import org.jpc.examples.metro.Metro;
import org.jpc.test.examples.metro.StressTest;
import org.junit.BeforeClass;

public class StressTestJpl extends StressTest {

	@BeforeClass
    public static void oneTimeSetUp() {
		LogicEngineProvider.logicEngine = new DefaultJplConfiguration().getEngine();
		assertTrue(Metro.loadAll());
    }

}
