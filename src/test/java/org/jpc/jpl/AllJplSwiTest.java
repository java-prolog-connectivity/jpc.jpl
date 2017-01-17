package org.jpc.jpl;

import static org.jpc.jpl.TestEngineLoader.loadSwiLogtalk;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		JplPrologEngineTestSuite.class,
		JplLogtalkEngineTestSuite.class})
public class AllJplSwiTest {
	@BeforeClass
	public static void setUp() {
		//loadSwi();
		loadSwiLogtalk();
	}
}
