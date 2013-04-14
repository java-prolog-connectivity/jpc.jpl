package org.jpc.jpl;

import static org.jpc.engine.provider.PrologEngineProviderManager.setPrologEngineProvider;

import org.jpc.engine.jpl.JplYapDriver;
import org.jpc.engine.logtalk.driver.LogtalkEngineProfile;
import org.jpc.engine.provider.SimpleEngineProvider;
import org.junit.BeforeClass;

public class JplYapLogtalkEngineTestSuite extends JplLogtalkEngineTestSuite {
	@BeforeClass
	public static void setUp() {
		setPrologEngineProvider(new SimpleEngineProvider(new LogtalkEngineProfile(new JplYapDriver()).createPrologEngine()));
	}
}
