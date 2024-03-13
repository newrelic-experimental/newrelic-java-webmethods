package com.softwareag.app.tn.eda.processors;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public class IEventProcessorCommand {

	@Trace
	public void execute(EventProcessorCommandContext[] var1) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","IEventProcessorCommand",getClass().getSimpleName(),"execute");
		Weaver.callOriginal();
	}
}
