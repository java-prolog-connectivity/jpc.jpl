package org.jpc.engine.jpl;

import java.io.File;

import jpl.JPL;

import org.jpc.engine.prolog.PrologEngine;
import org.jpc.engine.prolog.configuration.EngineConfigurationException;
import org.jpc.engine.prolog.configuration.UniquePrologEngineConfiguration;
import org.minitoolbox.Preferences.MissingPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DefaultJplConfiguration extends UniquePrologEngineConfiguration {

	private static volatile boolean engineInstanceRunning = false; //it can be only one (JPL managed) PrologEngine per JVM, then this variable is global
	
	private static Logger logger = LoggerFactory.getLogger(DefaultJplConfiguration.class);
	public static final String JPL_LIBRARY_NAME = "JPL";
	//public static final String JPLPATH_ENV_VAR = "JPLPATH"; //environment variable with the path to the JPL library. This will determine if the prolog engine is SWI or YAP
	private String jplPath;
	
	@Override
	public PrologEngine createPrologEngine() {
		PrologEngine prologEngine = super.createPrologEngine();
		engineInstanceRunning = true;
		return prologEngine;
	}
	
	@Override
	public boolean isInstanceRunning() {
		return engineInstanceRunning;
	}
	
	@Override
	public boolean isConfigured() {
		return jplPath != null;
	}
	
	@Override
	public void configure() {
		JPL.setDTMMode(false); //so all the variables (including the ones starting with '_') will be returned. Otherwise those variables will be excluded from the result. The anonymous variable '_' is never returned.
		try {
			String jplPath = getJplPathPropertyOrThrow();
			if(new File(jplPath).exists())
				this.jplPath = jplPath;
			else
				throw new EngineConfigurationException("The JPL library directory does not exist: " + jplPath);
			JPL.setNativeLibraryDir(jplPath); //configuring the JPL path according to an environment variable.
		} catch(MissingPropertyException e) {
			logger.warn("The directory of the native JPL library has not been configured.");
			logger.warn("If the 'java.library.path' property is set JPL will try to find the native library from there. Otherwise from the default OS search paths."); 
		}
	}

	public String getJplPathPropertyOrThrow() {
		return preferences.getVarOrThrow(getJplPathPropertyVar());
	}
	
	public abstract String getJplPathPropertyVar();
//	public String getJplPathPropertyVar() {
//		return JPLPATH_ENV_VAR;
//	}
	
	@Override
	protected PrologEngine basicCreatePrologEngine() {
		return new JplPrologEngine();
	}
	
	@Override
	public String getLibraryName() {
		return JPL_LIBRARY_NAME;
	}
	
}
