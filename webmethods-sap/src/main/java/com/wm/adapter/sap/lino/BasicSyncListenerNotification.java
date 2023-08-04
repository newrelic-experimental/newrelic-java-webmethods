package com.wm.adapter.sap.lino;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.adk.notification.NotificationResults;
import com.wm.data.IData;

@Weave(type = MatchType.BaseClass)
public abstract class BasicSyncListenerNotification {	
	
	public abstract String receiveServiceName();
	
	@Trace
	protected NotificationResults process(String tid, IData pipeline) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","SAP","BasicSyncListenerNotification",receiveServiceName());
		NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.CUSTOM_LOW, true, "SAP-Inbound", "WebMethods","SAP","Inbound",receiveServiceName());
		return Weaver.callOriginal();
	}
}
