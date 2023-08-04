package com.wm.adk.notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.webmethods.adapter.WMAdapterUtils;
import com.wm.adk.cci.record.WmRecord;

@Weave
public abstract class WmSynchronousNotification  extends WmNotification {

	@Trace
	public SyncNotificationResults invokeService(WmRecord rec)  {
		List<String> names = new ArrayList<>();
		names.add("Custom");
		names.add("WebMethods");
		names.add("WmSynchronousNotification");
		names.add("invokeService");
		if(_serviceName != null) {
			String serviceFullName = _serviceName.getFullName();
			if(serviceFullName != null && !serviceFullName.isEmpty()) {
				names.add(serviceFullName);
				NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.CUSTOM_HIGH, true, "ServiceInvoke", "Notification/Invoke",serviceFullName);
			}
		}
		String[] arrayOfNames = new String[names.size()];
		names.toArray(arrayOfNames);
		NewRelic.getAgent().getTracedMethod().setMetricName(arrayOfNames);

		Map<String, Object> attributes = new HashMap<>();
		WMAdapterUtils.addNSName(attributes, "NotificationNode", _notificationNodeName);
		WMAdapterUtils.addNSName(attributes, "PubRecNode", _pubRecNodeName);
		WMAdapterUtils.addNSName(attributes, "RepRecNode", _repRecNodeName);
		WMAdapterUtils.addNSName(attributes, "ReqRecNode", _reqRecNodeName);
		WMAdapterUtils.addAttribute(attributes, "AdapterType", _adapterTypeName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@SuppressWarnings("rawtypes")
	@Trace
	private SyncNotificationResults invokePublishAndWaitService(WmRecord data, Map pubAndWaitDetails) {
		return Weaver.callOriginal();
	}
	
	@Trace
	private SyncNotificationResults serviceInvoke(WmRecord rec) {
		return Weaver.callOriginal();
	}
}
