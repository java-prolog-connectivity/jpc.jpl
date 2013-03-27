package org.jpc.engine.jpl;

import jpl.JPL;

import org.jpc.engine.prolog.PrologEngine;
import org.jpc.engine.prolog.configuration.PrologEngineConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultJplConfiguration extends PrologEngineConfiguration {

	private static Logger logger = LoggerFactory.getLogger(DefaultJplConfiguration.class);
	
	public static final String JPL_LIBRARY_NAME = "JPL";
	public static final String JPLPATH_ENV_VAR = "JPLPATH"; //environment variable with the path to the JPL library. This will determine if the prolog engine is SWI or YAP
	
	private String jplPath;
	
	@Override
	public boolean configure() {
		JPL.setDTMMode(false); //so all the variables (including the ones starting with '_') will be returned. Otherwise those variables will be excluded from the result. The anonymous variable '_' is never returned.
		jplPath = getJplPath();
		if(jplPath != null && !jplPath.isEmpty()) {
			JPL.setNativeLibraryDir(jplPath); //configuring the JPL path according to an environment variable.
		} else {
			logger.warn("The variable " + JPLPATH_ENV_VAR + " indicating the directory of the native library has not been configured in the preferences.");
			logger.warn("If the 'java.library.path' property is set JPL will try to find the native library from there. Otherwise from the default OS search paths."); 
		}
		return true;
	}

	public String getJplPath() {
		return preferences.getVarOrThrow(getJplPathEnvVar());
	}
	
	public String getJplPathEnvVar() {
		return JPLPATH_ENV_VAR;
	}
	
	@Override
	protected PrologEngine basicCreatePrologEngine() {
		return new JplPrologEngine();
	}
	
	@Override
	public String getLibraryName() {
		return JPL_LIBRARY_NAME;
	}
	
}
