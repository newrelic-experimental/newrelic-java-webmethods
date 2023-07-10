package com.wm.adapter.sap.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.webmethods.sap.WMSAPUtils;
import com.sap.conn.idoc.IDocDocument;
import com.sap.conn.jco.server.JCoServerContext;
import com.wm.adapter.sap.idoc.IDocDocumentList;

@Weave
public abstract class Listener {
	
	@Trace
	private void handleIDocRequest(JCoServerContext serverCtx, String functionName, IDocDocumentList iDocList, String queueName) {
		List<String> names = new ArrayList<>();
		names.add("Custom");
		names.add("WebMethods");
		names.add("SAP");
		names.add("Listener");
		names.add("handleIDocRequest");
		if(functionName != null && !functionName.isEmpty()) {
			names.add(functionName);
		} else {
			names.add("UnknownFunction");
		}
		if(queueName != null && !queueName.isEmpty()) {
			names.add(queueName);
		}
		String[] metricNames = new String[names.size()];
		names.toArray(metricNames);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricNames);
		HashMap<String, Object> attributes = new HashMap<>();
		WMSAPUtils.addIDocDocumentList(attributes, iDocList);
		NewRelic.getAgent().getInsights().recordCustomEvent("IDocumentList", attributes);

		
		Weaver.callOriginal();
		
		for(int i=0; i<iDocList.size();i++) {
			IDocDocument doc = iDocList.get(i);
			attributes.clear();
			WMSAPUtils.addIDocDocument(attributes, doc);
			NewRelic.getAgent().getInsights().recordCustomEvent("IDocument", attributes);
		}
	}

}
