package org.jpc.engine.jpl;



public class JplSwiDriver extends JplDriver {
	public static final String SWI = "SWI";
	public static final String JPLPATH_SWI_ENV_VAR = "JPLPATH_SWI"; 
	
	@Override
	public String getJplPathPropertyVar() {
		return JPLPATH_SWI_ENV_VAR;
	}
	
	@Override
	public String getEngineName() {
		return SWI;
	}

}

