package org.jpc.jpl;

import static org.jpc.jpl.TestEngineLoader.loadYapLogtalk;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Ignore
@RunWith(Suite.class)
@Suite.SuiteClasses({
		JplPrologEngineTestSuite.class,
		JplLogtalkEngineTestSuite.class})
public class AllJplYapTest extends JplLogtalkEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		//loadYap();
		loadYapLogtalk();
	}
}
