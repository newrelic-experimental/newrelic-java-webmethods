package com.wm.adapter.sap.listener;

import java.util.ArrayList;
import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.server.JCoServerContext;

@Weave
public abstract class Listener {

	@Trace(dispatcher = true)
	public void handleRequest(JCoServerContext serverCtx, JCoFunction function) {
		List<String> names = new ArrayList<>();
		names.add("Custom");
		names.add("WebMethods");
		names.add("SAP");
		names.add("Listener");
		names.add("handleRequest");
		String functionName = function.getName();
		if(functionName != null && !functionName.isEmpty()) {
			names.add(functionName);
		} else {
			names.add("UnknownFunction");
		}
		String[] metricNames = new String[names.size()];
		names.toArray(metricNames);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricNames);

		Weaver.callOriginal();

	}
}
