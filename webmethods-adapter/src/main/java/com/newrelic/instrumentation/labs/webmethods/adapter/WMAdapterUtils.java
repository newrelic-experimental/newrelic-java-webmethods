package com.newrelic.instrumentation.labs.webmethods.adapter;

import java.util.Map;

import com.wm.lang.ns.NSName;

public class WMAdapterUtils {

	
	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(attributes != null && key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}
	
	public static void addNSName(Map<String, Object> attributes, String key, NSName nsName) {
		if(nsName != null) {
			addAttribute(attributes, key, nsName.getFullName());
		}
	}
}
