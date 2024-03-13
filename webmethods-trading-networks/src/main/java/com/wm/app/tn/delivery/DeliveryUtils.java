package com.wm.app.tn.delivery;

import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;
import com.wm.app.tn.doc.BizDocEnvelope;
import com.wm.data.IData;

@Weave
public class DeliveryUtils {

	@Trace
	public static IData invokeActiveTransfer(String serviceName, IData wscIn, boolean invokeInAsyncMode, boolean isLargeDoc) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName("Custom","WebMethods","DeliveryUtils","invokeActiveTransfer",serviceName);
		traced.addCustomAttribute("IsLargeDoc", isLargeDoc);
		traced.addCustomAttribute("InvokeInAsyncMode", invokeInAsyncMode);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static IData invokeLocalService(String serviceName, IData input, boolean invokeInAsyncMode) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName("Custom","WebMethods","DeliveryUtils","invokeLocalService",serviceName);
		traced.addCustomAttribute("InvokeInAsyncMode", invokeInAsyncMode);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static IData invokeService(DeliveryService service, IData svcInput)  {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		String serviceName = service != null ? service.getName() : null;
		if(serviceName != null) {
			traced.setMetricName("Custom","WebMethods","DeliveryUtils","invokeLocalService",serviceName);
		}
		if(service != null) {
			HashMap<String, Object> attributes = new HashMap<>();
			TNUtils.reportDeliveryService(attributes, service);
			traced.addCustomAttributes(attributes);
		}
		return Weaver.callOriginal();
	}
	
	@Trace
	public static IData invokeRemoteServiceWithAlias(String serviceName, String remoteServerAlias, IData input, boolean invokeInAsyncMode) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		if(serviceName != null) {
			traced.setMetricName("Custom","WebMethods","DeliveryUtils","invokeLocalService",serviceName);
		}
		traced.addCustomAttribute("RemoteServerAlias", remoteServerAlias);
		traced.addCustomAttribute("InvokeInAsyncMode", invokeInAsyncMode);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static IData invokeRemoteInHttp(String serviceName, String remoteServerAlias, IData input, boolean invokeInAsyncMode) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		if(serviceName != null) {
			traced.setMetricName("Custom","WebMethods","DeliveryUtils","invokeLocalService",serviceName);
		}
		traced.addCustomAttribute("RemoteServerAlias", remoteServerAlias);
		traced.addCustomAttribute("InvokeInAsyncMode", invokeInAsyncMode);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static DeliveryJob createQueuedJob(BizDocEnvelope doc, String qname) {
		DeliveryJob job = Weaver.callOriginal();
		return job;
	}
	
	@Trace(async = true)
	public static void notifyTaskFailure(GuaranteedJob job) {
		if(job.token != null) {
			job.token.linkAndExpire();
			job.token = null;
		}
		Weaver.callOriginal();
	}
}
