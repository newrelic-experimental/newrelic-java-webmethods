package com.softwareag.app.tn.eda;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class EDAClientInterface {

	@Trace(dispatcher = true)
	public static void publish(AbstractEventFactory eventTypeImpl, Object... objects) {
		String eventTypeStr = "Unknown";
		if(eventTypeImpl != null) {
			EventTypes eventType = eventTypeImpl.getEventType();
			if(eventType != null) {
				eventTypeStr = eventType.getEventType();
			}
		}
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","EDA","EDAClientInterface","publish",eventTypeStr);
		Weaver.callOriginal();
	}
}
