package org.jpc.jpl;


import org.jpc.util.config.EngineConfigurationManager;

public class TestEngineLoader {

    public static void loadSwi() {
        EngineConfigurationManager.setDefault(EngineConfigurationManager.createFromFile("jpc_swi.settings"));
    }

    public static void loadSwiLogtalk() {
        EngineConfigurationManager.setDefault(EngineConfigurationManager.createFromFile("jpc_swi_logtalk.settings"));
    }

    public static void loadYap() {
        EngineConfigurationManager.setDefault(EngineConfigurationManager.createFromFile("jpc_yap.settings"));
    }

    public static void loadYapLogtalk() {
        EngineConfigurationManager.setDefault(EngineConfigurationManager.createFromFile("jpc_yap_logtalk.settings"));
    }

}
