package com.wm.app.b2b.server.dispatcher;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.dispatch.WmMessageHeaders;
import com.wm.msg.IMessage;

@Weave(type = MatchType.BaseClass)
public abstract class AbstractExecutionTask {

	@Trace(dispatcher = true)
	protected void process(IMessage msg) {
		WmMessageHeaders headers = new WmMessageHeaders(msg);
		if(headers != null) {
			NewRelic.getAgent().getTransaction().acceptDistributedTraceHeaders(TransportType.Other, headers);
		}
		Weaver.callOriginal();
	}
	
	protected void handleProcessingException(Throwable ex) {
		NewRelic.noticeError(ex);
		Weaver.callOriginal();
	}
	
	protected void handlePreProcessingException(Throwable ex) {
		NewRelic.noticeError(ex);
		Weaver.callOriginal();
	}
}
