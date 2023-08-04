package com.newrelic.instrumentation.labs.webmethods.sap.montior;

import java.util.logging.Level;

import com.newrelic.agent.config.AgentConfig;
import com.newrelic.agent.config.AgentConfigListener;
import com.newrelic.api.agent.Config;
import com.newrelic.api.agent.Logger;
import com.newrelic.api.agent.NewRelic;

public class NRConfigurationListener implements AgentConfigListener {
	
	private static final String FREQUENCY = "WebMethods.SAP.Listener.frequency";
	private static final String ENABLED = "WebMethods.SAP.Listener.enabled";
	
	public void processConfig(Config config) {
		Logger logger = NewRelic.getAgent().getLogger();
		logger.log(Level.FINE, "Processing Agent Config");
		Object obj1 = config.getValue(ENABLED);
		if(obj1 != null) {
			NewRelic.getAgent().getLogger().log(Level.FINE, "Setting the value of Listener enabled using input {0}", obj1);
			if(obj1 instanceof Boolean) {
				Boolean b = (Boolean)obj1;
				WMListenerUtils.setEnabled(b);
				NewRelic.getAgent().getLogger().log(Level.FINE, "Set the value of Listener enabled to {0}", b);
			} else if(obj1 instanceof String) {
				String s = (String)obj1;
				Boolean b = Boolean.parseBoolean(s);
				WMListenerUtils.setEnabled(b);
				NewRelic.getAgent().getLogger().log(Level.FINE, "Set the value of Listener enabled to {0}", b);
			}
		} else {
			logger.log(Level.FINE, "SAP Monitor enabled not found in newrelic.yml");
		}
		
		Object obj2 = config.getValue(FREQUENCY);
		if(obj2 != null) {
			NewRelic.getAgent().getLogger().log(Level.FINE, "Setting the value of Listener frequency using input {0}", obj2);
			if(obj2 instanceof Number) {
				Number num = (Number)obj2;
				WMListenerUtils.rescheduleListener(num.longValue());
			} else if(obj2 instanceof String) {
				try {
					String s = (String)obj2;
					long l = Long.parseLong(s);
					WMListenerUtils.rescheduleListener(l);
				} catch (NumberFormatException e) {
					NewRelic.getAgent().getLogger().log(Level.FINE, "Failed to parse listener frequency from {0}", obj2);
				}
			}
		} else {
			logger.log(Level.FINE, "SAP Monitor frequency not found in newrelic.yml");
		}

	}
	
	@Override
	public void configChanged(String appName, AgentConfig agentConfig) {
		processConfig(agentConfig);
	}

}
