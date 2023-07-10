package com.wm.app.b2b.client;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.isclient.WebMethodsUtils;
import com.wm.data.IData;
import com.wm.util.Values;

@Weave
public class TContext {

	@Trace
	public IData clusterInvokeJob(String ifc, String svc, IData data)  {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","TContext","clusterInvokeJob",ifc,svc);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);

		return Weaver.callOriginal();
	}
	
	@Trace
	public IData invokeJob(String ifc, String svc, IData data) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","TContext","invokeJob",ifc,svc);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);

		return Weaver.callOriginal();
	}
	
	@Trace
	public IData invokeTx(String tid, String ifc, String svc, IData data) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","TContext","invokeTx",ifc,svc);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		return Weaver.callOriginal();
	}
	
	@Trace
	public Values invokeTx(String tid, String ifc, String svc, Values data) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","TContext","invokeTx",ifc,svc);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		return Weaver.callOriginal();
	}
}
