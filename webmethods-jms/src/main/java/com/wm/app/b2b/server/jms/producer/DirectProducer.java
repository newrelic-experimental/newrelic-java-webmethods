package com.wm.app.b2b.server.jms.producer;

import javax.jms.Message;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.app.b2b.server.jms.message.IDataMessage;

@Weave(type = MatchType.Interface)
public abstract class DirectProducer {

	@Trace
	public void send(IDataMessage var1) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","JMS","DirectProducer",getClass().getSimpleName(),"send");
		Weaver.callOriginal();
	}

	@Trace
	public Message sendAndWait(String var1, int var2, IDataMessage var3, long var4) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","JMS","DirectProducer",getClass().getSimpleName(),"sendAndWait");
		return Weaver.callOriginal();
	}

}
