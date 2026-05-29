package com.newrelic.instrumentation.webmethods10.isclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.newrelic.agent.config.AgentConfig;
import com.newrelic.agent.config.AgentConfigListener;
import com.newrelic.agent.config.ConfigService;
import com.newrelic.agent.service.ServiceFactory;
import com.newrelic.api.agent.Config;
import com.newrelic.api.agent.NewRelic;
import com.wm.lang.flow.FlowService;
import com.wm.lang.ns.NSName;

public class WebMethodsFiltering implements AgentConfigListener {

	private static WebMethodsFiltering INSTANCE = null;
	private static final String FLOWSERVICE_IGNORES_PROPERTY = "WebMethods.FlowService.ignores";
	private static List<String> flowServiceIgnoreNames = new ArrayList<>();
	private static Map<K, V>
	
	static {
		if(INSTANCE == null) {
			INSTANCE = new WebMethodsFiltering();
			ConfigService configService = ServiceFactory.getConfigService();
			configService.addIAgentConfigListener(INSTANCE);
			Config config = NewRelic.getAgent().getConfig();
			Object obj = config.getValue(FLOWSERVICE_IGNORES_PROPERTY);
			if(obj != null) {
				addIgnores(obj);
			}
			
		}
		
	}
	
	private WebMethodsFiltering() {
		
	}
	
	public static boolean ignore(Object object) {
		
		if(object instanceof FlowService) {
			FlowService flow = (FlowService)object;
			NSName name = flow.getNSName();
			if(name != null) {
				String fullName = name.getFullName();
				return flowServiceIgnoreNames.contains(fullName);
			}
			return false;
		}
		return false;
	}

	@Override
	public void configChanged(String appName, AgentConfig agentConfig) {
		Object obj = agentConfig.getProperty(FLOWSERVICE_IGNORES_PROPERTY);
		addIgnores(obj);
	}
	
	private static void addIgnores(Object obj) {
		if(obj != null) {
			if(obj instanceof String) {
				String value = (String)obj;
				if(!value.isEmpty()) {
					String[] ignores = value.split(",");
					if(ignores.length > 0) {
						List<String> list = Arrays.asList(ignores);
						flowServiceIgnoreNames.addAll(list);
					}
				}
			} else {
				NewRelic.getAgent().getLogger().log(Level.FINE, "Found value for WebMethods.FlowService.ignores but it was not a String, it is {0}", obj);
			}
		}
		
	}
}
