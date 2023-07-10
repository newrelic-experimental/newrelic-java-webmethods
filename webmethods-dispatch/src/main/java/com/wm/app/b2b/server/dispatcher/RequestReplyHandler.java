package com.wm.app.b2b.server.dispatcher;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.dispatch.WmMessageHeaders;

@Weave
public abstract class RequestReplyHandler {

	@Trace
	public DispatcherMessage publishAndWait(String uuid, DispatcherMessage msg, Producer producer, long wait) {
		WmMessageHeaders outHeaders = new WmMessageHeaders(msg);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(outHeaders);
		DispatcherMessage result = Weaver.callOriginal();
		
		WmMessageHeaders inHeaders = new WmMessageHeaders(result);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(inHeaders);		
		
		return result;
	}
	
	@Trace
	public String publishReq(String uuid, DispatcherMessage msg, Producer producer, long wait) {
		WmMessageHeaders outHeaders = new WmMessageHeaders(msg);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(outHeaders);

		return Weaver.callOriginal();
	}
}
