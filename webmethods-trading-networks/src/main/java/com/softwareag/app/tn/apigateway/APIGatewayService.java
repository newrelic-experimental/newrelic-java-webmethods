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
public class APIGatewayService {

	@Trace(dispatcher = true)
	public static List<APIDocument> listAPI(APISearchDocument searchDocument) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAPISearchDocument(attributes, searchDocument);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public static void addAPIsToPartner(List<String> apiIDs, String partnerID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportStringCollection(attributes, "API-Ids", apiIDs);
		TNUtils.reportAttribute(attributes, "PartnerId", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public static void addAPIsToPartnerGroup(List<String> apiIDs, String partnerGroupID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportStringCollection(attributes, "API-Ids", apiIDs);
		TNUtils.reportAttribute(attributes, "PartnerGroupId", partnerGroupID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public static void deleteAPIsFromPartner(List<String> apiIDs, String partnerID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportStringCollection(attributes, "API-Ids", apiIDs);
		TNUtils.reportAttribute(attributes, "PartnerId", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public static void deleteAPIsFromPartnerGroup(List<String> apiIDs, String partnerGroupId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportStringCollection(attributes, "API-Ids", apiIDs);
		TNUtils.reportAttribute(attributes, "PartnerGroupId", partnerGroupId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public static List<APIDocument> getAPIsForPartner(String partnerID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerId", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public static List<APIDocument> getAPIsForPartnerGroup(String partnerGroupID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerGroupId", partnerGroupID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public static APIDocument getAPIDetails(String apiID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "API-Id", apiID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public static APIApplicationAccessToken getAccessToken(String partnerID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerId", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public static Map<String, String> getPartnerAssociation(String partnerGroupID, String apiID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerGroupId", partnerGroupID);
		TNUtils.reportAttribute(attributes, "API-Id", apiID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public static void deletePartnerApplication(String partnerID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerId", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public static APIInvocationResult listAPIInvocations(APIInvocationSearchDocument searchDocument) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAPIInvocationSearchDocument(attributes, searchDocument);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
 		return Weaver.callOriginal();
	}

}
