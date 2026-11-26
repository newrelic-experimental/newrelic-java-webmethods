package com.softwareag.is.apigateway;

import java.net.URL;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.HTTPHeaderWrapper;
import com.wm.net.HttpHeader;

@Weave
public abstract class APIGRequestProcessorImpl {

	@Trace(dispatcher = true)
	public APIGState processRequest(APIGState beforeRequestMediation) {
		Transaction transaction = NewRelic.getAgent().getTransaction();
		HttpHeader header = beforeRequestMediation.getReqHeader();
		if (header != null) {
			HTTPHeaderWrapper wrapper = new HTTPHeaderWrapper(header);
			transaction.acceptDistributedTraceHeaders(TransportType.HTTP, wrapper);
		}
		URL url = beforeRequestMediation.getReqURI();
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("RequestURL", url.toString());
		return Weaver.callOriginal();
	}
}
