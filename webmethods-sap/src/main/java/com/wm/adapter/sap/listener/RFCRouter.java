package com.wm.adapter.sap.listener;

import java.util.ArrayList;
import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.data.IData;

@Weave
public abstract class RFCRouter {

	@Trace
	public static void inboundProcess(RFCListener rfcL, String rfcName, String svcName, IData pipeline, String tid, int action) {
		List<String> names = new ArrayList<>();
		names.add("Custom/WebMethods/SAPAdapter/RFCRouter");
		if(rfcName != null && !rfcName.isEmpty()) {
			names.add(rfcName);
		}
		if(svcName != null && !svcName.isEmpty()) {
			names.add(svcName);
		}
		String[] array = new String[names.size()];
		names.toArray(array);
		NewRelic.getAgent().getTracedMethod().setMetricName(array);
		Weaver.callOriginal();
	}
}
