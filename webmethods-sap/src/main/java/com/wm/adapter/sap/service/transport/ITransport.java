package com.wm.adapter.sap.service.transport;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.data.IData;

@Weave(type = MatchType.BaseClass)
public abstract class ITransport {

	@Trace
	protected void doInboundProcess(IData pipeline)  {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","SAP","ITransport",getClass().getSimpleName(),"doInboundProcess");
		Weaver.callOriginal();
	}
	
	@Trace
	protected void doExecute(String tid, IData pipeline) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName("Custom","WebMethods","SAP","ITransport",getClass().getSimpleName(),"doExecute");
		traced.addCustomAttribute("TID", tid);
		Weaver.callOriginal();
	}
	
	@Trace
	protected void doConfirm(String tid, IData pipeline) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName("Custom","WebMethods","SAP","ITransport",getClass().getSimpleName(),"doConfirm");
		traced.addCustomAttribute("TID", tid);
		Weaver.callOriginal();
	}
}
