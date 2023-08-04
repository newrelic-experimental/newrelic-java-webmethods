package com.newrelic.instrumentation.labs.webmethods.sap.montior;

import java.lang.instrument.Instrumentation;
import java.util.logging.Level;

import com.newrelic.agent.config.ConfigService;
import com.newrelic.agent.service.ServiceFactory;
import com.newrelic.api.agent.Config;
import com.newrelic.api.agent.NewRelic;

public class WMSAPPreMain {

	public static void premain(String s, Instrumentation inst) {
		NewRelic.getAgent().getLogger().log(Level.FINE, "Call to WMSAPPreMain.premain");
		ConfigService  configService = ServiceFactory.getConfigService();
		if(configService != null) {
			NRConfigurationListener listener = new NRConfigurationListener();
			configService.addIAgentConfigListener(listener);
			Config config = NewRelic.getAgent().getConfig();
			listener.processConfig(config);
			WMListenerUtils.initializeListener();
		} else {
			NewRelic.getAgent().getLogger().log(Level.FINE, "Failed to start WMSAP Config Listener because the Config Service is not available");
		}
	}
}
