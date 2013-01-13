package org.jpc.examples.metro.jpl;

import static junit.framework.Assert.assertTrue;

import org.jpc.engine.jpl.DefaultJplYapConfiguration;
import org.jpc.examples.metro.StressTest;
import org.jpc.examples.metro.imp.MetroExample;
import org.jpc.util.concurrent.ThreadLocalPrologEngine;
import org.junit.BeforeClass;


public class StressTestJpl extends StressTest {

	@BeforeClass
    public static void oneTimeSetUp() {
		ThreadLocalPrologEngine.setPrologEngine(new DefaultJplYapConfiguration().getEngine());
		assertTrue(MetroExample.loadAll());
    }

}
