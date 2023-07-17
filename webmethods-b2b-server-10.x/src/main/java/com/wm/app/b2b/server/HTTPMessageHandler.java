package com.wm.app.b2b.server;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.HTTPHeaderWrapper;
import com.wm.net.HttpHeader;

@Weave
public abstract class HTTPMessageHandler {

	@Trace(dispatcher = true)
	public boolean process(ProtocolState state)  {
		String requestURL = state.getRequestUrl();
		if(requestURL != null && !requestURL.isEmpty()) {
			NewRelic.getAgent().getTracedMethod().addCustomAttribute("RequestURL", requestURL);
		}
		Transaction transaction = NewRelic.getAgent().getTransaction();
		if(!transaction.isWebTransaction()) {
			transaction.convertToWebTransaction();
		}
		transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "HttpHandler", "HTTPMessageHandler");
		HttpHeader header = state.getRequestHeader();
		if(header != null) {
			HTTPHeaderWrapper wrapper = new HTTPHeaderWrapper(header);
			transaction.acceptDistributedTraceHeaders(TransportType.HTTP, wrapper);
		}
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("RequestURI", state.getHttpRequestUrl());
		return Weaver.callOriginal();
	}
}
