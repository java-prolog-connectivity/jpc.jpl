package org.jpc.test.examples.metro.jpl;

import static junit.framework.Assert.assertTrue;

import org.jpc.LogicEngineProvider;
import org.jpc.engine.jpl.DefaultJplConfiguration;
import org.jpc.examples.metro.Metro;
import org.jpc.test.engine.LogicEngineTestSuite;
import org.jpc.test.examples.metro.MetroTestSuite;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({LogicEngineTestSuite.class, MetroTestSuite.class})
public class MetroJplTestSuite extends MetroTestSuite {
	
	@BeforeClass
    public static void oneTimeSetUp() {
		LogicEngineProvider.logicEngine = new DefaultJplConfiguration().getEngine();
		assertTrue(Metro.loadAll());
    }

}
