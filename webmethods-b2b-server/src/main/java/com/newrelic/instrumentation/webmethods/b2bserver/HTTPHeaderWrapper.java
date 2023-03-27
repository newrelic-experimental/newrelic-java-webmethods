package com.newrelic.instrumentation.webmethods.b2bserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.Headers;
import com.wm.net.HttpHeader;

public class HTTPHeaderWrapper implements Headers {
	
	private HttpHeader header = null;
	
	public HTTPHeaderWrapper(HttpHeader h) {
		header = h;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.HTTP;
	}

	@Override
	public String getHeader(String name) {
		return header.getFieldValue(name);
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
		header.addField(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		header.addField(name, value);
	}

	@Override
	public Collection<String> getHeaderNames() {
		Map<String, String> fields = header.getFieldsMap();
		return fields != null ? fields.keySet() : Collections.emptyList();
	}

	@Override
	public boolean containsHeader(String name) {
		return getHeaderNames().contains(name);
	}

}
