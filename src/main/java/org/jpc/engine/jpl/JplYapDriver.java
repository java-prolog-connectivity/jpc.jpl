package org.jpc.engine.jpl;

import org.jpc.util.supportedengines.EngineDescription;
import org.jpc.util.supportedengines.Yap;



public class JplYapDriver extends JplDriver {
	public static final String YAP = "YAP";
	public static final String JPLPATH_YAP_ENV_VAR = "JPLPATH_YAP";
	
	@Override
	public String getJplPathPropertyVar() {
		return JPLPATH_YAP_ENV_VAR;
	}
	
	@Override
	public EngineDescription getEngineDescription() {
		return new Yap();
	}
	
}
