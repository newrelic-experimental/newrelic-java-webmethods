package com.newrelic.instrumentation.webmethods.b2bserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.Headers;
import com.wm.io.comm.IMessage;

public class IMessageWrapper implements Headers {
	
	private IMessage message = null;
	
	public IMessageWrapper(IMessage msg) {
		message = msg;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.MESSAGE;
	}

	@Override
	public String getHeader(String name) {
		Properties props = message.getProperties();
		return props != null ? props.getProperty(name) : null;
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
		message.getProperties().put(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		message.getProperties().put(name, value);
	}

	@Override
	public Collection<String> getHeaderNames() {
		List<String> list = new ArrayList<>();
		Properties props = message.getProperties();
		Set<Object> keys = props.keySet();
		for(Object key : keys) {
			list.add(key.toString());
		}
		return list;
	}

	@Override
	public boolean containsHeader(String name) {
		return getHeaderNames().contains(name);
	}

}
