package com.wm.app.tn.db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;
import com.wm.data.IData;

@Weave
abstract class BDMultiDocOperations {

	@Trace(dispatcher = true)
	public static int deleteDocuments(String docId, String sysStatus, String userStatus, String docTypeId, boolean delRelated) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocTypeId", docTypeId);
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportAttribute(attributes, "SystemStatus", sysStatus);
		TNUtils.reportAttribute(attributes, "UserStatus", userStatus);
		TNUtils.reportAttribute(attributes, "DeleteRelated", delRelated);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@SuppressWarnings("rawtypes")
	@Trace(dispatcher = true)
	public static ArrayList executeQuery(String query, Timestamp min_ts, Timestamp max_ts) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Query", query);
		TNUtils.reportAttribute(attributes, "StartTimestamp", min_ts);
		TNUtils.reportAttribute(attributes, "EndTimestamp", max_ts);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public static IData getRelatedDocs(String docId, String groupId) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportAttribute(attributes, "GroupId", groupId);
		traced.addCustomAttributes(attributes);
		
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public static String[] poll(String rec_id) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Rec_Id", rec_id);
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public static String[] pollForUser(String username) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("username", username);
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public static IData query(SimpleDocQuery query, boolean agg, int pageSize, int maxR, int qryT, int threshold, String queryId) {
		return Weaver.callOriginal();
	}
	
	@SuppressWarnings("rawtypes")
	@Trace(dispatcher = true)
	public static ArrayList updateTimestamp(String[] ids, Timestamp[] times, String query) {
		return Weaver.callOriginal();
	}
	
	@SuppressWarnings("rawtypes")
	@Trace(dispatcher = true)
	public static ArrayList updateTimestamp(String[] docId, Timestamp[] new_crt, Timestamp[] new_last, String query) {
		return Weaver.callOriginal();
	}
	
}
