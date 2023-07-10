package com.softwareag.is.apigateway;

import java.net.URL;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class APIGRequestProcessorImpl {

	@Trace(dispatcher = true)
	public APIGState processRequest(APIGState beforeRequestMediation) {
		URL url = beforeRequestMediation.getReqURI();
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("RequestURL", url.toString());
		return Weaver.callOriginal();
	}
}
