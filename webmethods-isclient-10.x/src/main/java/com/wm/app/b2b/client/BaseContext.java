package com.wm.app.b2b.client;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.isclient.WebMethodsUtils;
import com.wm.data.IData;
import com.wm.util.Values;

@Weave(type = MatchType.BaseClass)
public abstract class BaseContext {

	@Trace(dispatcher = true)
	protected IData clusterInvoke(Values clusterServers, String curSessionID, String curServer, int maxTries, String intrfc, String svc, IData inData)  {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","Context",getClass().getSimpleName(),"clusterInvoke",intrfc,svc);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		return Weaver.callOriginal();
	}
	
	@Trace
	protected Values clusterSvcInvoke(String ifc, String svc, Values in) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","Context",getClass().getSimpleName(),"clusterSvcInvoke",ifc,svc);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		return Weaver.callOriginal();
	}

	@Trace
	protected IData clusterSvcInvoke(String ifc, String svc, IData in) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","Context",getClass().getSimpleName(),"clusterSvcInvoke",ifc,svc);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		return Weaver.callOriginal();
	}

}
