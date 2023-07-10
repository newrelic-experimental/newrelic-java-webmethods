package com.wm.app.b2b.server.dispatcher.comms;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.dispatch.WmMessageHeaders;
import com.wm.app.b2b.server.dispatcher.DispatcherMessage;

@Weave(type = MatchType.BaseClass)
public abstract class BrokerTransport {

	public boolean publish(DispatcherMessage[] msgs) {
		for(DispatcherMessage msg : msgs) {
			WmMessageHeaders headers = new WmMessageHeaders(msg);
			NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(headers);
		}
		return Weaver.callOriginal();
	}

	public DispatcherMessage[] getEvents(int maxNoOfEvents, long ackSequenceNo, int waitMsecs) {
		DispatcherMessage[] msgs = Weaver.callOriginal();
		if(msgs != null && msgs.length > 0) {
			for(DispatcherMessage msg : msgs) {
				WmMessageHeaders headers = new WmMessageHeaders(msg);
				NewRelic.getAgent().getTransaction().acceptDistributedTraceHeaders(TransportType.Other, headers);
			}
		}
		return msgs;
	}
}
