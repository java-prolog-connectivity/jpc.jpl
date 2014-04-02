package org.jpc.engine.jpl;

import static org.jpc.engine.prolog.ReturnSpecifierConstants.RETURN_SERIALIZED_SPECIFIER;
import static org.jpc.engine.prolog.ReturnSpecifierConstants.RETURN_TERM_SPECIFIER;
import static org.jpc.engine.prolog.ThreadModel.MULTI_THREADED;
import static org.jpc.engine.prolog.ThreadModel.SINGLE_THREADED;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;

import jpl.JPL;

import org.jpc.Jpc;
import org.jpc.converter.catalog.jrefterm.TermToJRefTermTypeConverter;
import org.jpc.converter.catalog.serialized.ToSerializedConverter;
import org.jpc.engine.listener.DriverStateListener;
import org.jpc.engine.prolog.PrologEngineInitializationException;
import org.jpc.engine.prolog.driver.PrologEngineFactory;
import org.jpc.engine.prolog.driver.UniquePrologEngineDriver;
import org.jpc.term.Compound;
import org.jpc.term.Term;
import org.jpc.term.jrefterm.JRefTermType;
import org.jpc.util.JpcPreferences;
import org.jpc.util.engine.supported.EngineDescription;
import org.minitoolbox.collections.CollectionsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JplDriver extends UniquePrologEngineDriver<JplEngine> {

	private static final Logger logger = LoggerFactory.getLogger(JplDriver.class);
	public static final String JPL_LIBRARY_NAME = "JPL";
	//public static final String JPLPATH_ENV_VAR = "JPLPATH"; //environment variable with the path to the JPL library. This will determine if the prolog engine is SWI or YAP

	private static final Collection<DriverStateListener> stateListeners = CollectionsUtil.createWeakSet();
	
	private String jplPath;
	private final String jplPathPropertyVar;

	public JplDriver(EngineDescription engineDescription, String jplPathPropertyVar, JpcPreferences preferences) {
		super(engineDescription, preferences);
		this.jplPathPropertyVar = jplPathPropertyVar;
	}
	
	public String getJplPath() {
		return jplPath;
	}

	public void setJplPath(String jplPath) {
		this.jplPath = jplPath;
	}

	@Override
	protected boolean isInstanceRunning() {
		return JplEngine.prologEngine != null;
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
	
	public String getJplPathPropertyVar() {
		return jplPathPropertyVar;
	}

	/**
	 * 
	 * @return a JPL Prolog engine with multi-threading support.
	 */
	public JplEngine createMTPrologEngine() {
		return createPrologEngine(new PrologEngineFactory<JplEngine>() {
			@Override
			public JplEngine createPrologEngine() {
				return new JplEngine(MULTI_THREADED);
			}
		});
	}
	
	@Override
	protected synchronized JplEngine createPrologEngine(PrologEngineFactory<JplEngine> basicFactory) {
		JplEngine.prologEngine = super.createPrologEngine(basicFactory);
		return JplEngine.prologEngine;
	}
	
	@Override
	protected PrologEngineFactory<JplEngine> defaultBasicFactory() {
		return new PrologEngineFactory<JplEngine>() {
			@Override
			public JplEngine createPrologEngine() {
				return new JplEngine(SINGLE_THREADED);
			}
		};
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
	
	
	
	public static jpl.Term evalAsTerm(jpl.Term evalTermJpl) {
		Term evalTerm = JplBridge.fromJplToJpc(evalTermJpl);
		Term expTerm = evalTerm.arg(1);
		Object result = Jpc.getDefault().fromTerm(expTerm);
		Term resultTerm;
		Compound returnSpecifierTerm = (Compound) evalTerm.arg(2);
		if(returnSpecifierTerm.getNameString().equals(RETURN_TERM_SPECIFIER)) {
			resultTerm = Jpc.getDefault().toTerm(result);
		} else if(returnSpecifierTerm.getNameString().equals(RETURN_SERIALIZED_SPECIFIER)) {
			resultTerm = new ToSerializedConverter().toTerm((Serializable)result, Compound.class, Jpc.getDefault());
		} else {
			JRefTermType jRefTermType = new TermToJRefTermTypeConverter().fromTerm((Compound) returnSpecifierTerm, JRefTermType.class, Jpc.getDefault());
			resultTerm = jRefTermType.toTerm(result, Jpc.getDefault());
		}
		return JplBridge.fromJpcToJpl(resultTerm);
	}
	
	public static Object evalAsObject(jpl.Term evalTermJpl) {
		Term evalTerm = JplBridge.fromJplToJpc(evalTermJpl);
		Term targetTerm = evalTerm.arg(1);
		return Jpc.getDefault().fromTerm(targetTerm);
	}

	public static void newWeakJRefTerm(Object ref, jpl.Term jrefTermJpl) {
		Compound jrefTerm = (Compound) JplBridge.fromJplToJpc(jrefTermJpl);
		Jpc.getDefault().newWeakJRefTerm(ref, jrefTerm);
	}

}

