package org.jpc.engine.jpl;



public class DefaultJplYapConfiguration extends DefaultJplConfiguration {
	public static final String YAP = "yap";
	public static final String JPLPATH_YAP_ENV_VAR = "JPLPATH_YAP";
	
	@Override
	public String getJplPathEnvVar() {
		return JPLPATH_YAP_ENV_VAR;
	}
	
	@Override
	public String getEngineName() {
		return YAP;
	}
	
}
