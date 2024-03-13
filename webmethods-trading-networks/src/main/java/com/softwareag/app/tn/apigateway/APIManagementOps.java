package com.softwareag.app.tn.apigateway;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;

@Weave
public abstract class APIManagementOps {
	
	@Trace(dispatcher = true)
	public void addAPIsToPartner(List<String> apiIDs, String partnerID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportStringCollection(attributes, "API-Ids", apiIDs);
		TNUtils.reportAttribute(attributes, "PartnerID", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public void addAPIsToPartnerGroup(List<String> apiIDs, String partnerGroupID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportStringCollection(attributes, "API-Ids", apiIDs);
		TNUtils.reportAttribute(attributes, "PartnerGroupID", partnerGroupID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public void deleteAPIsFromPartner(List<String> apiIDs, String partnerID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportStringCollection(attributes, "API-Ids", apiIDs);
		TNUtils.reportAttribute(attributes, "PartnerID", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public void deleteAPIsFromPartnerGroup(List<String> apiIDs, String partnerGroupID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportStringCollection(attributes, "API-Ids", apiIDs);
		TNUtils.reportAttribute(attributes, "PartnerGroupID", partnerGroupID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public void deletePartnerApplication(String partnerID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerID", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public APIApplicationAccessToken getAccessToken(String partnerID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerID", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public APIDocument getAPIDetails(String apiID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "API-ID", apiID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public List<APIDocument> getAPIList(APISearchDocument searchDocument) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAPISearchDocument(attributes, searchDocument);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public List<APIDocument> getAPIsForPartner(String partnerID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerID", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public List<APIDocument> getAPIsForPartnerGroup(String partnerGroupID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerGroupID", partnerGroupID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public Map<String, String> getPartnerAssociation(String partnerGroupID, String apiID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerGroupID", partnerGroupID);
		TNUtils.reportAttribute(attributes, "APIID", apiID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
}
