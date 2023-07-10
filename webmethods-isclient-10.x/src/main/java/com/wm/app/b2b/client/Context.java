package com.wm.app.b2b.client;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.isclient.WebMethodsUtils;
import com.wm.data.IData;
import com.wm.lang.ns.NSName;
import com.wm.util.Values;

@Weave(type = MatchType.BaseClass)
public class Context {

	@Trace
	public IData invoke(String ifc, String svc, IData data, boolean isConnect) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","Context",getClass().getSimpleName(),"invoke",ifc,svc);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		return Weaver.callOriginal();
	}
	
	@Trace
	public Values invoke(String ifc, String svc, Values data) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","Context",getClass().getSimpleName(),"invoke",ifc,svc);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		return Weaver.callOriginal();
	}
	
	@Trace
	public InvokeThread invokeThreaded(NSName svcName, Values data) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","Context",getClass().getSimpleName(),"invokeThreaded",svcName == null ? null : svcName.getFullName());
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		return Weaver.callOriginal();
	}

	@Trace
	public InvokeThread invokeThreaded(String ifc, String svc, Values data) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","Context",getClass().getSimpleName(),"invokeThreaded",ifc,svc);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		return Weaver.callOriginal();
	}

}
