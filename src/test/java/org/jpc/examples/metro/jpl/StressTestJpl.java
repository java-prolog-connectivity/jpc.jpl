package org.jpc.examples.metro.jpl;

import static junit.framework.Assert.assertTrue;

import org.jpc.engine.jpl.DefaultJplConfiguration.DefaultJplYapConfiguration;
import org.jpc.examples.metro.StressTest;
import org.jpc.examples.metro.imp.MetroExample;
import org.jpc.util.ThreadLocalLogicEngine;
import org.junit.BeforeClass;


public class StressTestJpl extends StressTest {

	@BeforeClass
    public static void oneTimeSetUp() {
		ThreadLocalLogicEngine.setLogicEngine(new DefaultJplYapConfiguration().getEngine());
		assertTrue(MetroExample.loadAll());
    }

}
