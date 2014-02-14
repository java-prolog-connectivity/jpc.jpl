package org.jpc.engine.jpl;

import org.jpc.util.engine.supported.EngineDescription;
import org.jpc.util.engine.supported.Swi;

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

