package com.softwareag.client.registry.http;

import java.net.URI;

import com.newrelic.api.agent.HttpParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.isclient.OutboundWrapper;
import com.wm.net.HttpContext;

@Weave
public class HttpClient {

	@Trace(leaf = true)
	public static HttpResponse sendRequest(HttpRequest httpRequest) {
		HttpParameters params = HttpParameters.library("WebMethods-ISClient").uri(URI.create(httpRequest.getRequestUrl())).procedure("sendRequest").noInboundHeaders().build();
		NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		HttpResponse resp = Weaver.callOriginal();
		
		return resp;
	}
	
	@SuppressWarnings("unused")
	private static HttpContext createContext(HttpRequest httpRequest) {
		HttpContext ctx = Weaver.callOriginal();
		OutboundWrapper wrapper = new OutboundWrapper(ctx);
		NewRelic.getAgent().getTracedMethod().addOutboundRequestHeaders(wrapper);
		return ctx;
	}
}
