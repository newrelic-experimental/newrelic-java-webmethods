package com.softwareag.wsstack.adapter.api;

import javax.xml.namespace.QName;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class Adapter {

	
	public abstract AdapterContext getInContext();

	public abstract AdapterContext getOutContext();

	@Trace(dispatcher = true)
	public void execute() {
		AdapterContext in = getInContext();
		if(in != null) {
			String inService = in.getServiceName();
			String operationName = null;
			try {
				QName opName = in.getOperationName();
				operationName = opName.toString();
			} catch (AdapterContextException e) {
			}
			if(operationName != null) {
				NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Adapter",getClass().getSimpleName(),"execute",inService,operationName);
			} else {
				NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Adapter",getClass().getSimpleName(),"execute",inService);
			}
		}
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public void execute(AdapterContext var1, AdapterContext var2) {
		if(var1 != null) {
			String inService = var1.getServiceName();
			String operationName = null;
			try {
				QName opName = var1.getOperationName();
				operationName = opName.toString();
			} catch (AdapterContextException e) {
			}
			if(operationName != null) {
				NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Adapter",getClass().getSimpleName(),"execute",inService,operationName);
			} else {
				NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Adapter",getClass().getSimpleName(),"execute",inService);
			}
		}
		Weaver.callOriginal();
	}
}
