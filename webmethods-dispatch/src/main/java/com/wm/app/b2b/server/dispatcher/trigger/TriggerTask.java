package com.wm.app.b2b.server.dispatcher.trigger;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class TriggerTask {

	protected String _triggerName = Weaver.callOriginal();
	
	@Trace(dispatcher = true)
	public void run() {
		if(_triggerName != null) {
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","TriggerTask",_triggerName,"run");
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "Trigger", "WebMethods","Trigger",_triggerName);
		} else {
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","TriggerTask","run");
		}
		Weaver.callOriginal();
	}
}
