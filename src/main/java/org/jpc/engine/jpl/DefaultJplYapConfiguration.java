package org.jpc.engine.jpl;

import static org.jpc.engine.prolog.KnownSupportedEngines.YAP;


public class DefaultJplYapConfiguration extends DefaultJplConfiguration {
	public static final String JPLPATH_YAP_ENV_VAR = "JPLPATH_YAP";
	
	@Override
	public String getJplPathEnvVar() {
		return JPLPATH_YAP_ENV_VAR;
	}
	
	@Override
	public String getLogicEngineName() {
		return YAP;
	}

}