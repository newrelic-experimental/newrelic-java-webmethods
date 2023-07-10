package com.wm.lang.flow;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.isclient.WebMethodsUtils;
import com.wm.data.IData;
import com.wm.util.Values;

@Weave
public class FlowMapInvoke extends FlowInvoke {

	public FlowMapInvoke(Values val) {
		super(val);
	}

	public FlowMapInvoke(IData data) {
		super(data);
	}

	@Trace
	public void invokeService(IData pipeline, FlowState fs) {
		String name = getName();
		String serviceName = service != null ? service.getFullName() : "UnknownService";
		
		String[] metricName = WebMethodsUtils.getMetricName("Custom","WebMethods","FlowMapInvoke","invokeService",name,serviceName);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		
		Weaver.callOriginal();
	}
}
