package com.wm.app.b2b.server.invoke;

import java.util.Iterator;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.WebMethodsUtils;
import com.wm.app.b2b.server.BaseService;
import com.wm.data.IData;
import com.wm.lang.ns.NSName;

@Weave(type = MatchType.Interface)
public abstract class InvokeChainProcessor {

	@SuppressWarnings("rawtypes")
	@Trace
	public void process(Iterator var1, BaseService var2, IData var3, ServiceStatus var4)  {
		NSName serviceName = var2.getNSName();
		String name = serviceName != null ? serviceName.getFullName() : null;
		String[] metricName = WebMethodsUtils.getMetricName("Custom","WebMethods","InvokeChainProcessor",getClass().getSimpleName(),"process",name);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		Weaver.callOriginal();
	}
}
