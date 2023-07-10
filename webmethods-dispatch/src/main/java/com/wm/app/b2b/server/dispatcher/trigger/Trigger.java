package com.wm.app.b2b.server.dispatcher.trigger;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.dispatch.WmMessageHeaders;
import com.wm.msg.IMessage;

@Weave(type = MatchType.BaseClass)
public abstract class Trigger {

	public abstract String getName();
	
	@Trace(dispatcher = true)
	public boolean processMessage(IMessage msg)  {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","Dispatcher","Trigger",getName());
		WmMessageHeaders headers = new WmMessageHeaders(msg);
		NewRelic.getAgent().getTransaction().acceptDistributedTraceHeaders(TransportType.Other, headers);
		return Weaver.callOriginal();
	}
}
