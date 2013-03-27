package org.jpc.engine.jpl;



public class DefaultJplSwiConfiguration extends DefaultJplConfiguration {
	public static final String SWI = "swi";
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

