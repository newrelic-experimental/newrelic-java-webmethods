package com.newrelic.instrumentation.webmethods.b2bserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.Headers;

public class PropertiesWrapper implements Headers {
	
	private Properties properties = null;
	
	public PropertiesWrapper(Properties props) {
		properties = props;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.MESSAGE;
	}

	@Override
	public String getHeader(String name) {
		if(properties == null) return null;
		return properties.getProperty(name);
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
		properties.setProperty(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		properties.setProperty(name, value);
	}

	@Override
	public Collection<String> getHeaderNames() {
		List<String> list = new ArrayList<>();
		Enumeration<?> names = properties.propertyNames();
		while(names.hasMoreElements()) {
			list.add(names.nextElement().toString());
		}
		return null;
	}

	@Override
	public boolean containsHeader(String name) {
		return getHeaderNames().contains(name);
	}

}
