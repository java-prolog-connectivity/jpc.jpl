package org.jpc.examples.metro.jpl;

import static junit.framework.Assert.assertTrue;

import org.jpc.engine.jpl.DefaultJplYapConfiguration;
import org.jpc.examples.metro.MetroExample;
import org.jpc.examples.metro.StressTest;
import org.jpc.util.concurrent.ThreadLocalPrologEngine;
import org.junit.BeforeClass;


public class StressTestJpl extends StressTest {

	@BeforeClass
    public static void oneTimeSetUp() {
		ThreadLocalPrologEngine.setPrologEngine(new DefaultJplYapConfiguration().createPrologEngine());
		assertTrue(MetroExample.loadAll());
    }

}
