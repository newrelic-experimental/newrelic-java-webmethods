package com.newrelic.instrumentation.webmethods.b2bserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.newrelic.agent.config.AgentConfig;
import com.newrelic.agent.config.AgentConfigListener;
import com.newrelic.agent.service.ServiceFactory;
import com.newrelic.api.agent.Config;
import com.newrelic.api.agent.NewRelic;
import com.wm.lang.flow.FlowElement;
import com.wm.lang.flow.FlowRoot;
import com.wm.lang.flow.FlowState;

public class WebMethodsUtils implements AgentConfigListener {
	
	public static final String KEY_PREFIX = "WebMethods-";
	private static final String SERVICE_IGNORE = "webmethods.services.ignore";
	private static final String PACKAGE_LIST = "wm.server.packages:packageList";
	
	public static final List<String> servicesToIgnore;
	
	static {
		ServiceFactory.getConfigService().addIAgentConfigListener(new WebMethodsUtils());
		servicesToIgnore = new ArrayList<>();
		Config config = NewRelic.getAgent().getConfig();
		processConfig(config);
	}
	
	private static void init() {
		servicesToIgnore.clear();
		servicesToIgnore.add(PACKAGE_LIST);
		NewRelic.getAgent().getLogger().log(Level.FINE, "Will not report service: {0}", PACKAGE_LIST);
	}
	
	private WebMethodsUtils() {
		
	}

	public static void addValue(Map<String, Object> attributes, String key, Object value) {
		if(attributes != null && key != null && !key.isEmpty() && value != null) {
			if(!key.startsWith(KEY_PREFIX)) {
				key = KEY_PREFIX + key;
			}
			attributes.put(key, value);
		}
	}
	
	public static void addFlowElement(Map<String,Object> attributes, FlowElement element) {
		boolean isRoot = element instanceof FlowRoot;
		String prefix = isRoot ? "FlowRoot-" : "";
		addValue(attributes,prefix+"DisplayType",element.getDisplayType());
		addValue(attributes,prefix+"FlowType",element.getFlowType());
		addValue(attributes,prefix+"Path",element.getPath());
		if (!isRoot) {
			FlowElement root = element.getFlowRoot();
			addValue(attributes, "RootElement", root.getName());
		}
	}
	
	public static void addFlowState(Map<String,Object> attributes, FlowState state) {
		addValue(attributes,"FlowState-CurrentPath",state.currentPath());
	}
	
	public static String[] getMetricName(String... names) {
		List<String> nameList = new ArrayList<>();
		for(String name : names) {
			if(name != null && !name.isEmpty()) {
				nameList.add(name);
			}
		}
		String[] namesArray = new String[nameList.size()];
		
		nameList.toArray(namesArray);
		return namesArray;
	}
	
	private static void processConfig(Config config) {
		init();
		String s = config.getValue(SERVICE_IGNORE);
		NewRelic.getAgent().getLogger().log(Level.FINE, "The configuration value of {0} is {1}", SERVICE_IGNORE,s);
		if(s != null && !s.isEmpty()) {
			String[] parts = s.split(",");
			
			for(String part : parts) {
				servicesToIgnore.add(part);
				NewRelic.getAgent().getLogger().log(Level.FINE, "Will not report service: {0}", part);
			}
		}
	}

	@Override
	public void configChanged(String appName, AgentConfig agentConfig) {
		processConfig(agentConfig);
	}

}
