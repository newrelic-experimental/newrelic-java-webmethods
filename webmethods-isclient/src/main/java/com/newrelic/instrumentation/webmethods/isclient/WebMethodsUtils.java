package com.newrelic.instrumentation.webmethods.isclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wm.lang.flow.FlowElement;
import com.wm.lang.flow.FlowRoot;
import com.wm.lang.flow.FlowState;

public class WebMethodsUtils {
	
	public static final String KEY_PREFIX = "WebMethods-";

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
//		addValue(attributes,prefix+"DisplayType",element.getDisplayType());
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
}
