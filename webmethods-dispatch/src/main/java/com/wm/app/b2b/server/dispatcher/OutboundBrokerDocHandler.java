package com.wm.app.b2b.server.dispatcher;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.dispatch.WmMessageHeaders;

@Weave(type = MatchType.BaseClass)
public abstract class OutboundBrokerDocHandler {

	@Trace
	public boolean publish(DispatcherMessage[] msgs) {
		for(DispatcherMessage msg : msgs) {
			WmMessageHeaders headers = new WmMessageHeaders(msg);
			NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(headers);
		}
		return Weaver.callOriginal();
	}
	
	@Trace
	protected void handleMessages(Object[] msgs)  {
		Weaver.callOriginal();
	}
}
