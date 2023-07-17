package com.wm.driver.comm.b2b;

import java.net.URI;
import java.util.HashMap;

import com.newrelic.api.agent.GenericParameters;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.IMessageWrapper;
import com.newrelic.instrumentation.webmethods.b2bserver.WebMethodsUtils;

@Weave(type = MatchType.Interface)
public abstract class WmLink {

	@Trace(dispatcher = true)
	public void send(WmMessage message) {
		HashMap<String, Object> attributes = new HashMap<>();
		WebMethodsUtils.addIMessage(attributes, message);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		IMessageWrapper wrapper = new IMessageWrapper(message);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(wrapper);
		URI uri = URI.create("wmlink://Send");
		GenericParameters params = GenericParameters.library("WMLink").uri(uri).procedure("send").build();
		NewRelic.getAgent().getTracedMethod().reportAsExternal(params);
		Weaver.callOriginal();
	}
}
