package com.wm.app.b2b.server;

import java.io.IOException;
import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.HTTPHeaderWrapper;
import com.newrelic.instrumentation.webmethods.b2bserver.WebMethodsUtils;

@Weave(type = MatchType.Interface)
public abstract class HTTPHandler {

	@Trace
	public boolean process(ProtocolState state) throws IOException, AccessException {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","HTTPHandler",getClass().getSimpleName(),"process");
		HashMap<String, Object> attributes = new HashMap<>();
		WebMethodsUtils.addValue(attributes,"RequestUrl",state.getRequestUrl());
		WebMethodsUtils.addValue(attributes,"ReqUrl",state.getReqUrl());
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		HTTPHeaderWrapper wrapper = new HTTPHeaderWrapper(state.getRequestHeader());
		NewRelic.getAgent().getTransaction().acceptDistributedTraceHeaders(TransportType.HTTP, wrapper);
		try {
			return Weaver.callOriginal();
		} catch (Exception e) {
			if(e instanceof IOException || e instanceof AccessException) {
				NewRelic.noticeError(e);
			}
			throw e;
		}
	}
}
