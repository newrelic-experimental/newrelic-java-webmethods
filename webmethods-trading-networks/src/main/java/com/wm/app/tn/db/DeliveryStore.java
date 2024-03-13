package com.wm.app.tn.db;

import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;
import com.wm.app.tn.delivery.DeliveryService;
import com.wm.app.tn.delivery.GuaranteedJob;
import com.wm.data.IData;

@Weave
public abstract class DeliveryStore {

	@Trace
	public static boolean deleteJob(String job_id) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("JobId", job_id);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static IData queryJobs(DeliveryJobQuery query, int pageSize, int maxRowCount, int queryTimeout, int threshold, String queryId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportDeliveryJobQuery(attributes, query);
		TNUtils.reportAttribute(attributes, "QueryId", queryId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static int deleteJobs(DeliveryJobQuery query) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportDeliveryJobQuery(attributes, query);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static boolean deleteService(String svc_nm) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ServiceName", svc_nm);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static int deleteServices(DeliveryServiceQuery query)  {
		HashMap<String, Object> attributes = new HashMap<>();
		if(query != null) {
			TNUtils.reportAttribute(attributes, "DeletingSql", query.toDeletingSql());
			TNUtils.reportAttribute(attributes, "Host", query.get(DeliveryServiceQuery.HOST));
			TNUtils.reportAttribute(attributes, "Port", query.get(DeliveryServiceQuery.PORT));
			TNUtils.reportAttribute(attributes, "ServiceName", query.get(DeliveryServiceQuery.SVC_NAME));
		}
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(async = true)
	public static GuaranteedJob dequeueJobWithTaskId(String queue, String taskId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Queue", queue);
		TNUtils.reportAttribute(attributes, "TaskId", taskId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		GuaranteedJob j = Weaver.callOriginal();
		if(j.token != null) {
			j.token.link();
		}
		return j;
	}
	
	@Trace(async = true)
	public static GuaranteedJob dequeueOldestJob(String queue)  {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Queue", queue);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		GuaranteedJob j = Weaver.callOriginal();
		if(j.token != null) {
			j.token.link();
		}
		return j;
	}
	
	@Trace
	public static GuaranteedJob[] dequeueOldestJobs(String queue, int jobCount) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Queue", queue);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace(async = true)
	public static boolean insertJob(GuaranteedJob j) {
		if(j.token != null) {
			j.token.link();
		} else {
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				j.token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportGuaranteedJob(attributes, j);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static boolean insertService(DeliveryService s) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportDeliveryService(attributes, s);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static IData queryServices(DeliveryServiceQuery query, int pageSize, int maxRowCount, int queryTimeout, int threshold, String queryId) {
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
	public static boolean updateJob(GuaranteedJob j) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportGuaranteedJob(attributes, j);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static boolean updateService(DeliveryService s) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportDeliveryService(attributes, s);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
}
