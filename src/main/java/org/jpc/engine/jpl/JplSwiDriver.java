package org.jpc.engine.jpl;

import org.jpc.util.supportedengines.EngineDescription;
import org.jpc.util.supportedengines.Swi;

public class JplSwiDriver extends JplDriver {
	public static final String JPLPATH_SWI_ENV_VAR = "JPLPATH_SWI"; 
	
	@Override
	public String getJplPathPropertyVar() {
		return JPLPATH_SWI_ENV_VAR;
	}
	
	@Override
	public EngineDescription getEngineDescription() {
		return new Swi();
	}

}

