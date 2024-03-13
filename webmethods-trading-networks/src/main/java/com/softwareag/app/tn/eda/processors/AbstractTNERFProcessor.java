package com.softwareag.app.tn.eda.processors;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.BaseClass)
public abstract class AbstractTNERFProcessor {

	@Trace
	public Boolean call() {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","AbstractTNERFProcessor",getClass().getSimpleName(),"call");
		return Weaver.callOriginal();
	}
	
}
