package org.jpc.engine.jpl;

import static org.jpc.engine.prolog.KnownSupportedEngines.SWI;


public class DefaultJplSwiConfiguration extends DefaultJplConfiguration {
	public static final String JPLPATH_SWI_ENV_VAR = "JPLPATH_SWI"; 
	
	@Override
	public String getJplPathEnvVar() {
		return JPLPATH_SWI_ENV_VAR;
	}
	
	@Override
	public String getEngineName() {
		return SWI;
	}

}

