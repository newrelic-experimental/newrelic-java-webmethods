package com.wm.driver.comm.b2b;

import java.net.URI;

import com.newrelic.api.agent.GenericParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.IMessageWrapper;

@Weave(type = MatchType.Interface)
public abstract class WmLink {

	@Trace
	public void send(WmMessage var1) {
		IMessageWrapper wrapper = new IMessageWrapper(var1);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(wrapper);
		URI uri = URI.create("wmlink://Send");
		GenericParameters params = GenericParameters.library("WMLink").uri(uri).procedure("send").build();
		NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		Weaver.callOriginal();
	}
}
