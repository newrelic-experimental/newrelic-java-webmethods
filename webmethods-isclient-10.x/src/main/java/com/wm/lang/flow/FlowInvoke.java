package com.wm.lang.flow;

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
public abstract class FlowInvoke extends FlowElement {
	
	NSName service = Weaver.callOriginal();

	public FlowInvoke(Values val) {
		super(val);
	}
	
	public FlowInvoke(IData data) {
		super(data);
	}
	
	@Trace(dispatcher = true)
	public void invoke(FlowState state) {
		String name = getName();
		String serviceName = service != null ? service.getFullName() : "UnknownService";
		String[] metricName = WebMethodsUtils.getMetricName("Custom","WebMethods","FlowInvoke","invoke",name,serviceName);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		Weaver.callOriginal();
	}
}
