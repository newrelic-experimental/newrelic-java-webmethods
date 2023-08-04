package com.newrelic.instrumentation.webmethods.b2bserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.Headers;

public class WMHeaders implements Headers {
	
	private HashMap<String, String> attributes;
	
	public WMHeaders() {
		attributes = new HashMap<>();
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.MESSAGE;
	}

	@Override
	public String getHeader(String name) {
		return attributes.get(name);
	}

	@Override
	public Collection<String> getHeaders(String name) {
		List<String> list = new ArrayList<>();
		String value = getHeader(name);
		if(value != null && !value.isEmpty()) {
			list.add(value);
		}
		
		return list;
	}

	@Override
	public void setHeader(String name, String value) {
		attributes.put(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		attributes.put(name, value);
	}

	@Override
	public Collection<String> getHeaderNames() {
		return attributes.keySet();
	}

	@Override
	public boolean containsHeader(String name) {
		return getHeaderNames().contains(name);
	}

}
