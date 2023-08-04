package com.wm.app.b2b.server;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.WMHeaders;
import com.wm.data.IData;
import com.wm.lang.ns.NSName;

@Weave
public abstract class ServiceThread extends AbstractISThread {
	
	private NSName service = Weaver.callOriginal();
	
	@NewField
	protected WMHeaders headers = null;
	
	public ServiceThread(NSName service, Session session, IData input) {
		super(session);
	}

	@Trace(dispatcher = true)
	protected void execute() {
		String serviceName = service != null ? service.getFullName() : "UnknownService";
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","ServiceThread","execute",serviceName);
		if(headers != null) {
			NewRelic.getAgent().getTransaction().acceptDistributedTraceHeaders(TransportType.Other, headers);
		}
		Weaver.callOriginal();
	}
}
