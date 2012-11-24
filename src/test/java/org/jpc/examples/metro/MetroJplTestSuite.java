package org.jpc.examples.metro;

import org.jpc.engine.jpl.DefaultJplConfiguration.DefaultJplYapConfiguration;
import org.jpc.examples.metro.MetroTestSuite;
import org.jpc.util.ThreadLocalLogicEngine;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({//LogicEngineTestSuite.class, 
	MetroTestSuite.class})
public class MetroJplTestSuite {//extends MetroTestSuite {
	
	@BeforeClass
    public static void oneTimeSetUp() {
		ThreadLocalLogicEngine.setLogicEngine(new DefaultJplYapConfiguration().getEngine());
		//ThreadLocalLogicEngine.setLogicEngine(new DefaultJplSwiConfiguration().getEngine());
		//assertTrue(MetroImp.loadAll());
    }

}
