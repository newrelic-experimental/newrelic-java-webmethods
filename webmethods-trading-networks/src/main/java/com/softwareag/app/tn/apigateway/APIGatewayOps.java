package com.softwareag.app.tn.apigateway;

import java.util.HashMap;
import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;
import com.softwareag.app.tn.util.ws.IWebService;
import com.softwareag.app.tn.util.ws.StatusValidator;
import com.wm.app.tn.profile.ProfileSummary;
import com.wm.data.IData;
import com.wm.net.HTTPMethod;

@Weave
public abstract class APIGatewayOps {

	@Trace
	private Object executeRestServiceInternal(String srvPath, HTTPMethod method, IData input, StatusValidator validator, Map<String, String> reqParams, IWebService.ResponseType responseType)  {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ServicePath", srvPath);
		TNUtils.reportHttpMethod(attributes, method);
		TNUtils.reportIWebService_ResponseType(attributes, responseType);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace
	protected void deleteApplication(String applicationID, String partnerID) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("ApplicationId", applicationID);
		traced.addCustomAttribute("PartnerId", partnerID);
		Weaver.callOriginal();
	}
	
	@Trace
	protected String getApplicationIDForPartner(String partnerID, boolean createFlag) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("CreateFlag", createFlag);
		traced.addCustomAttribute("PartnerId", partnerID);
		return Weaver.callOriginal();
	}
	
	@Trace
	protected ProfileSummary getProfileSummary(String partnerID) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("PartnerId", partnerID);
		return Weaver.callOriginal();
	}
}
