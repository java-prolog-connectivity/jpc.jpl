package org.jpc.examples.metro.jpl;

import org.jpc.examples.metro.MetroTestSuite;
import org.jpc.util.ThreadLocalLogicEngine;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This test suit exists only for testing individually the metro example for the jpl implementation.
 * It will be deleted soon...
 * @author sergioc
 *
 */
@RunWith(Suite.class)
@SuiteClasses({
	MetroTestSuite.class})
public class MetroJplTestSuite {
	
	@BeforeClass
    public static void oneTimeSetUp() {
		//ThreadLocalLogicEngine.setLogicEngine(new DefaultJplYapConfiguration().getEngine());
		//ThreadLocalLogicEngine.setLogicEngine(new DefaultJplSwiConfiguration().getEngine());
    }

}
