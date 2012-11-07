package org.jpc.engine.jpl;

import jpl.JPL;

import org.jpc.JpcPreferences;
import org.jpc.engine.BootstrapLogicEngine;
import org.jpc.engine.LogicEngineConfiguration;

public class DefaultJplConfiguration extends LogicEngineConfiguration {

	public static final String JPLPATH = "JPLPATH"; //path of the JPL library in the host computer. This will determine if the prolog engine is SWI or YAP
	
	@Override
	public void configure() {
		//configuring the JPL path according to an environment variable. So a JPL Prolog engine can be started
		JPL.setNativeLibraryDir(new JpcPreferences().getVarOrDie(JPLPATH));
	}

	@Override
	protected BootstrapLogicEngine createBootstrapLogicEngine() {
		return new JplLogicEngine();
	}
	
	@Override
	public boolean isConfigured() {
			return false; //TODO find a way to see if JPL has been initialized (in any case the initialization is quite light so it does not harm if it happens many times, but it should be fixed...)
		
		/**
		 * According to the JPL documentation, the getActualInitArgs() method returns null if the JPL Prolog engine has not been initialized 
		 * The problem in fact is that this throws an error, and it is not possible to initialize the logic engine afterwards
		 */
		/*
		try {
			return JPL.getActualInitArgs() != null;
		} catch(Error e) {
			return false;
		}
		*/
	}
	

	
}
