package com.newrelic.instrumentation.webmethods.dispatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.Headers;
import com.wm.app.b2b.server.dispatcher.DispatcherMessage;
import com.wm.msg.Header;
import com.wm.msg.IMessage;

public class WmMessageHeaders implements Headers {
	
	private IMessage message = null;
	
	public WmMessageHeaders(IMessage msg) {
		message = msg;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.MESSAGE;
	}

	@Override
	public String getHeader(String name) {
		Header header = message.getHeader(name);
		return header != null ? header.getValue() : null;
	}

	@Override
	public Collection<String> getHeaders(String name) {
		List<String> list = new ArrayList<>();
		String value = getHeader(name);
		if(value != null) {
			list.add(value);
		}
		return list;
	}

	@Override
	public void setHeader(String name, String value) {
		if(message instanceof DispatcherMessage) {
			((DispatcherMessage)message).setHeader(name, value);
		}
	} 

	@Override
	public void addHeader(String name, String value) {
		if(message instanceof DispatcherMessage) {
			((DispatcherMessage)message).setHeader(name, value);
		}
	}

	@Override
	public Collection<String> getHeaderNames() {
		Header[] headers = message.getHeaders();
		List<String> list = new ArrayList<>();
		if(headers != null) {
			for(Header header : headers) {
				String attr = header.getAttribute();
				if(attr != null) list.add(attr);
			}
		}
		return list;
	}

	@Override
	public boolean containsHeader(String name) {
		
		return getHeaderNames().contains(name);
	}

}
