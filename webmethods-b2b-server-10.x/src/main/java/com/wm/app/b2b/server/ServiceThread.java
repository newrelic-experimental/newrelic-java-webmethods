package com.wm.app.b2b.server;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.WeaveAllConstructors;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.lang.ns.NSName;

@Weave
public abstract class ServiceThread {
	
	@NewField
	private Token token = null;
	
	protected NSName service = Weaver.callOriginal();
	
	@WeaveAllConstructors
	public ServiceThread() {
		if(token == null) {
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}
	}

	@Trace(async = true)
	protected void execute() {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(service != null) {
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","ServiceThread","execute",service.getFullName());
		}
		Weaver.callOriginal();
		
	}
}
