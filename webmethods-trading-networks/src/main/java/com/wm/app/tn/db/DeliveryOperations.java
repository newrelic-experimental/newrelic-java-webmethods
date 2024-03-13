package com.wm.app.tn.db;

import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;
import com.wm.app.tn.delivery.GuaranteedJob;
import com.wm.app.tn.doc.BizDocEnvelope;
import com.wm.data.IData;

@SuppressWarnings("rawtypes")
@Weave
public abstract class DeliveryOperations {

	@Trace
	public static int getServices(Hashtable cache)  {
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean changeService(Object o, int op) {
		return Weaver.callOriginal();
	}

	@Trace
	public static IData query(DeliveryServiceQuery query, int pageSize, int maxR, int qryT, int threshold, String queryId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "QueryId", queryId);
		if(query != null) {
			TNUtils.reportAttribute(attributes, "DeletingSql", query.toDeletingSql());
			TNUtils.reportAttribute(attributes, "Host", query.get(DeliveryServiceQuery.HOST));
			TNUtils.reportAttribute(attributes, "Port", query.get(DeliveryServiceQuery.PORT));
			TNUtils.reportAttribute(attributes, "ServiceName", query.get(DeliveryServiceQuery.SVC_NAME));
		}
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static GuaranteedJob getAnyJob(String jobId, boolean content, boolean includeBizDocErrors) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "JobId", jobId);
		TNUtils.reportAttribute(attributes, "Content", content);
		TNUtils.reportAttribute(attributes, "IncludeBizDocErrors", includeBizDocErrors);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static List<GuaranteedJob> getTasks(String docId, boolean content, boolean isArchive) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportAttribute(attributes, "Content", content);
		TNUtils.reportAttribute(attributes, "IsArchive", isArchive);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static int getOtherJobs(Hashtable cache, String serverId, boolean content) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ServerId", serverId);
		TNUtils.reportAttribute(attributes, "Content", content);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static synchronized void clearActiveJobsCacheAndReload(Hashtable cache, String serverId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ServerId", serverId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace
	public static int getJobIds(Vector ids, String serverId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ServerId", serverId);
		TNUtils.reportAttribute(attributes, "Ids", ids);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean changeJob(String server_id, Object o, int op) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Server_Id", server_id);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static IData query(DeliveryJobQuery query, int pageSize, int maxR, int qryT, int threshold, String queryId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "QueryId", queryId);
		TNUtils.reportDeliveryJobQuery(attributes, query);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static int delete(DeliveryJobQuery query) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportDeliveryJobQuery(attributes, query);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static int reassign(String from, String to) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "From", from);
		TNUtils.reportAttribute(attributes, "To", to);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static int changeServer(String from, String to, boolean includeFailed) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "From", from);
		TNUtils.reportAttribute(attributes, "To", to);
		TNUtils.reportAttribute(attributes, "IncludeFailed", includeFailed);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static void notifyJobs(BizDocEnvelope doc) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportBizDocEnvelope(attributes, doc);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace
	public static boolean checkJob(Connection conn, String jobId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "JobId", jobId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static String getServiceName(String jobId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "JobId", jobId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static Collection<? extends GuaranteedJob> getAnyServerJobs(List<String> taskList, boolean includeContent, boolean includeBizDocErrors) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "IncludeContent", includeContent);
		TNUtils.reportAttribute(attributes, "IncludeBizDocErrors", includeBizDocErrors);
		TNUtils.reportAttribute(attributes, "TaskList", taskList);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

}
