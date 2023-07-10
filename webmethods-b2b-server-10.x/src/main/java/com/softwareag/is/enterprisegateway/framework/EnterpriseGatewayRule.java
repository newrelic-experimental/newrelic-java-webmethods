package com.softwareag.is.enterprisegateway.framework;

import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.WebMethodsUtils;
import com.softwareag.is.enterprisegateway.alert.EnterpriseGatewayAlert;
import com.wm.app.b2b.server.HTTPState;
import com.wm.net.HttpHeader;

@Weave
public abstract class EnterpriseGatewayRule {
	
	public abstract String getName();
	public abstract String getAction();
	public abstract String getRequestType();
	public abstract String getResourcePath();

	@Trace
	public void processRule(HTTPState httpState, EnterpriseGatewayAlert alertConfig, HttpHeader internalRequestHeader) {
		String[] metricName = WebMethodsUtils.getMetricName("Custom","EnterpriseGatewayRule",getName(),"processRule");
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName(metricName);
		HashMap<String, Object> attributes = new HashMap<>();
		WebMethodsUtils.addValue(attributes, "Action", getAction());
		WebMethodsUtils.addValue(attributes, "RequestType", getRequestType());
		WebMethodsUtils.addValue(attributes, "ResourcePath", getResourcePath());
		Weaver.callOriginal();
	}
}
