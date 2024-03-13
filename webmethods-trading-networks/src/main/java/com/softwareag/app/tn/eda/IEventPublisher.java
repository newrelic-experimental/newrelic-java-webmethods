package com.softwareag.app.tn.eda;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class IEventPublisher {
	
	@Trace
	public void publish(AbstractEventFactory eventType, Object... objects) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","IEventPublisher",getClass().getSimpleName(),"publish");
		Weaver.callOriginal();
	}
}
