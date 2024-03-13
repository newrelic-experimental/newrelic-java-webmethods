package com.wm.app.tn.delivery;

import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;
import com.wm.app.tn.doc.BizDocEnvelope;
import com.wm.app.tn.util.TNFixedData;
import com.wm.data.IData;

@SuppressWarnings("serial")
@Weave(type = MatchType.BaseClass)
public abstract class GuaranteedJob extends TNFixedData {
	
	public static Object[][] GuaranteedJobSchema() {
		return Weaver.callOriginal();
	}

	public GuaranteedJob() {
		super(GuaranteedJobSchema());
	}
	
	protected GuaranteedJob(Object[][] subSchema) {
		super(merge(GuaranteedJobSchema(), subSchema));
	}
	
	public GuaranteedJob(String jobId, String serverId, DeliveryService service, BizDocEnvelope bizdoc, IData svcInput,
			long ttw, int retryLimit, int retryFactor) throws DeliveryException {
		this();
	}
	
	@NewField
	public Token token = null;

	public abstract String getQueueName();

	public abstract String getJobId();

	public abstract String getServerId();

	public abstract String getClassification();
	public abstract DeliveryService getDeliveryService();
	public abstract DeliveryService getService();
	
	@Trace(async = true)
	public void delivering() {
		if(token != null) {
			token.link();
		} else {
			// see if we're in an active transaction and if so set token
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportGuaranteedJob(attributes, this);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}
	
	@Trace(async = true)
	public void done() {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportGuaranteedJob(attributes, this);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}
	
	@Trace(async = true)
	public void enqueue(String queue) {
		if(token != null) {
			token.link();
		} else {
			// see if we're in an active transaction and if so set token
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportGuaranteedJob(attributes, this);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Queue", queue);
		Weaver.callOriginal();		
	}
	
	@Trace(async = true)
	public void fail() {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportGuaranteedJob(attributes, this);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		NewRelic.noticeError("WebMethods Trading Networks Job Failed", attributes);
		Weaver.callOriginal();
	}
	
	@Trace(async = true)
	public void invoke() {
		if(token != null) {
			token.link();
		} else {
			// see if we're in an active transaction and if so set token
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportGuaranteedJob(attributes, this);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();		
	}
}
