package com.softwareag.app.tn.apigateway;

import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;

@Weave
public abstract class APIMonitoringOps {

	@Trace(dispatcher = true)
	public APIInvocationResult getAPIInvocationData(APIInvocationSearchDocument searchDocument) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAPIInvocationSearchDocument(attributes, searchDocument);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
}
