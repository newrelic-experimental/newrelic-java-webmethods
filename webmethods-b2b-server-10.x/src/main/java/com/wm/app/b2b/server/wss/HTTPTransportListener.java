package com.wm.app.b2b.server.wss;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.HTTPHeaderWrapper;
import com.wm.app.b2b.server.ProtocolState;
import com.wm.lang.websvc.WSDServiceName;
import com.wm.net.HttpHeader;

@Weave
public class HTTPTransportListener {

	@Trace(dispatcher = true)
	public boolean process(ProtocolState state) {
		String requestURL = state.getRequestUrl();
		if(requestURL != null && !requestURL.isEmpty()) {
			NewRelic.getAgent().getTracedMethod().addCustomAttribute("RequestURL", requestURL);
		}
		Transaction transaction = NewRelic.getAgent().getTransaction();
		HttpHeader header = state.getRequestHeader();
		if(header != null) {
			HTTPHeaderWrapper wrapper = new HTTPHeaderWrapper(header);
			transaction.acceptDistributedTraceHeaders(TransportType.HTTP, wrapper);
		}
		transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "HttpTransport", "HTTPTransportListener",getServiceName(state));
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("WebMethods-RequestURI", state.getHttpRequestUrl());
		return Weaver.callOriginal();
	}
	
	private String getServiceName(ProtocolState state) {
		WSDServiceName wsName = new WSDServiceName(state.getRequestUrl());
		
		return wsName.getServiceName();
		
	}
}
