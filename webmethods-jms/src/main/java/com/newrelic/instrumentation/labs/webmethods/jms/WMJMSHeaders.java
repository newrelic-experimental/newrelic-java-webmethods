package com.newrelic.instrumentation.labs.webmethods.jms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.Headers;
import com.wm.app.b2b.server.jms.message.JMSMessage;

public class WMJMSHeaders implements Headers {

	private JMSMessage message = null;
	
	public WMJMSHeaders(JMSMessage msg) {
		message = msg;
	}
	
	@Override
	public HeaderType getHeaderType() {
		return HeaderType.MESSAGE;
	}

	@Override
	public String getHeader(String name) {
		Message msg = message.getMessage();
		if(msg != null) {
			try {
				return msg.getStringProperty(name);
			} catch (JMSException e) {
			}
		}
		return null;
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
		Message msg = message.getMessage();
		if (msg != null) {
			try {
				msg.setStringProperty(name, value);
			} catch (JMSException e) {
			} 
		}
	}

	@Override
	public void addHeader(String name, String value) {
		Message msg = message.getMessage();
		if (msg != null) {
			try {
				msg.setStringProperty(name, value);
			} catch (JMSException e) {
			} 
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<String> getHeaderNames() {
		Message msg = message.getMessage();
		List<String> list = new ArrayList<>();
		if(msg != null) {
			try {
				Enumeration<String> propNames = msg.getPropertyNames();
				while(propNames.hasMoreElements()) {
					String name = propNames.nextElement();
					if(name != null && !name.isEmpty()) {
						list.add(name);
					}
				}
			} catch (JMSException e) {
			}
		}
		
		return list;
	}

	@Override
	public boolean containsHeader(String name) {
		return getHeaderNames().contains(name);
	}

}
