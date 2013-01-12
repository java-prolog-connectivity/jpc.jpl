package org.jpc.engine.jpl;

import jpl.JPL;

import org.jpc.engine.prolog.BootstrapPrologEngine;
import org.jpc.engine.prolog.PrologEngineConfiguration;
import org.jpc.util.JpcPreferences;

public abstract class DefaultJplConfiguration extends PrologEngineConfiguration {

	public static final String JPL_LIBRARY_NAME = "JPL";
	public static final String JPLPATH_ENV_VAR = "JPLPATH"; //environment variable with the path to the JPL library. This will determine if the prolog engine is SWI or YAP
	
	@Override
	public void configure() {
		//configuring the JPL path according to an environment variable. So a JPL Prolog engine can be started
		String jplPath = getJplPath();
		JPL.setNativeLibraryDir(jplPath);
	}

	public String getJplPath() {
		return new JpcPreferences().getVarOrDie(getJplPathEnvVar());
	}
	
	public String getJplPathEnvVar() {
		return JPLPATH_ENV_VAR;
	}
	
	@Override
	protected BootstrapPrologEngine createBootstrapEngine() {
		return new JplLogicEngine();
	}
	
	@Override
	public boolean isConfigured() {
			return false; //TODO find a way to see if JPL has been configured (in any case the JPL configuration is quite light so it does not harm if it happens many times, but it should be fixed...)
		
		/**
		 * According to the JPL documentation, the getActualInitArgs() method returns null if the JPL Prolog engine has not been initialized 
		 * The problem is that this throws an error, and it is not possible to initialize the logic engine afterwards
		 * Then we cannot test if JPL has already been initialized using that
		 */
		/*
		try {
			return JPL.getActualInitArgs() != null;
		} catch(Error e) {
			return false;
		}
		*/
	}
	
	@Override
	public String getLibraryName() {
		return JPL_LIBRARY_NAME;
	}
	
}
