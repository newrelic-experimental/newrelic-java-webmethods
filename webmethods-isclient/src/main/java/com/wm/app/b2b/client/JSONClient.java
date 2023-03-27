package com.wm.app.b2b.client;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.isclient.WebMethodsUtils;
import com.wm.lang.ns.NSName;

@Weave(type = MatchType.BaseClass)
public class JSONClient extends Context {

	@Trace
	public String invoke(String ifc, String svc, String jsonData) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","JSONClient",getClass().getSimpleName(),"invoke",ifc,svc);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		return Weaver.callOriginal();
	}
	
	@Trace
	public InvokeThread invokeThreaded(NSName svcName, String jsonData) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","JSONClient",getClass().getSimpleName(),"invokeThreaded",svcName == null ? null : svcName.getFullName());
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		return Weaver.callOriginal();
	}
	
	@Trace
	public InvokeThread invokeThreaded(final String ifc, final String svc, final String jsonData) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","JSONClient",getClass().getSimpleName(),"invokeThreaded",ifc,svc);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		return Weaver.callOriginal();
	}
}
