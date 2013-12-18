package org.jpc.engine.jpl;

import java.io.File;
import java.util.Collection;

import jpl.JPL;

import org.jpc.engine.listener.DriverStateListener;
import org.jpc.engine.prolog.PrologEngine;
import org.jpc.engine.prolog.PrologEngineInitializationException;
import org.jpc.engine.prolog.driver.UniquePrologEngineDriver;
import org.jpc.util.JpcPreferences;
import org.minitoolbox.collections.CollectionsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JplDriver extends UniquePrologEngineDriver {

	private static Logger logger = LoggerFactory.getLogger(JplDriver.class);
	public static final String JPL_LIBRARY_NAME = "JPL";
	//public static final String JPLPATH_ENV_VAR = "JPLPATH"; //environment variable with the path to the JPL library. This will determine if the prolog engine is SWI or YAP
	
	/**
	 * it can be only one (JPL managed) PrologEngine per JVM, then this variable is global
	 * it does not need to be declared volatile since it is only used by the (already synchronized) public methods isEnabled and createPrologEngine
	 */
	private static boolean jplSessionStarted = false; 
	

	private static Collection<DriverStateListener> stateListeners = CollectionsUtil.createWeakSet();
	
	private String jplPath;

	public JplDriver() {
		super();
	}
	
	public JplDriver(JpcPreferences preferences) {
		super(preferences);
	}
	
	public String getJplPath() {
		return jplPath;
	}

	public void setJplPath(String jplPath) {
		this.jplPath = jplPath;
	}

	@Override
	protected boolean isInstanceRunning() {
		return jplSessionStarted;
	}
	
	@Override
	public void readyOrThrow() {
		JPL.setDTMMode(false); //so all the variables (including the ones starting with '_') will be returned. Otherwise those variables will be excluded from the result. The anonymous variable '_' is never returned.
		if(jplPath == null) {
			jplPath = getDefaultJplPath();
		}
		if(jplPath != null) {
			if(!new File(jplPath).exists())
				throw new PrologEngineInitializationException("The JPL library directory does not exist: " + jplPath);
			JPL.setNativeLibraryDir(jplPath); //configuring the JPL path according to an environment variable.
		} else {
			//do nothing, it may be that the JVM argument java.library.path includes the path to the JPL library
			logger.warn("The directory of the native JPL library has not been configured.");
			logger.warn("If the 'java.library.path' property is set JPL will try to find the native library from there. Otherwise from the default OS search paths."); 
		}
	}

	public String getDefaultJplPath() {
		return getPreferences().getVar(getJplPathPropertyVar());
	}
	
	public abstract String getJplPathPropertyVar();
//	public String getJplPathPropertyVar() {
//		return JPLPATH_ENV_VAR;
//	}

	@Override
	protected PrologEngine basicCreatePrologEngine() {
		PrologEngine prologEngine = new JplEngine();
		jplSessionStarted = true;
		return prologEngine;
	}
	
	@Override
	public String getLibraryName() {
		return JPL_LIBRARY_NAME;
	}
	
	@Override
	public String getLicenseUrl() {
		return "http://www.swi-prolog.org/packages/jpl/java_api/lgpl.html";
	}
	
	@Override
	public String getSiteUrl() {
		return "http://www.swi-prolog.org/packages/jpl/";
	}
	
	@Override
	protected Collection<DriverStateListener> getListeners() {
		return stateListeners;
	}
}
