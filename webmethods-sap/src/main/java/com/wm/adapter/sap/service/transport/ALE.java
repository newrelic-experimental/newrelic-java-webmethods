package com.wm.adapter.sap.service.transport;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.data.IData;

@Weave
public abstract class ALE {

	private static ALE _singleton = Weaver.callOriginal();
	
	public abstract String getOutboundService();
	
	public abstract String getInboundService();
	
	@Trace
	public static void inboundProcess(IData pipeline) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		if (_singleton != null) {
			traced.addCustomAttribute("WebMethods-OutboundService", _singleton.getOutboundService());
			traced.addCustomAttribute("WebMethods-InboundService", _singleton.getInboundService());
		}
		Weaver.callOriginal();
	}
}
