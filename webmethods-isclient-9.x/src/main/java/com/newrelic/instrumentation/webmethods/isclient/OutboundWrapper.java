package com.newrelic.instrumentation.webmethods.isclient;

import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.OutboundHeaders;
import com.wm.net.HttpContext;

public class OutboundWrapper implements OutboundHeaders {
	
	private HttpContext ctx = null;
	
	public OutboundWrapper(HttpContext c) {
		ctx = c;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.HTTP;
	}

	@Override
	public void setHeader(String name, String value) {
		if(ctx != null) {
			ctx.setRequestHeader(name, value);
		}
	}

}
