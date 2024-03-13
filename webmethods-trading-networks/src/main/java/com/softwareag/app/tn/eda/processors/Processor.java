package com.softwareag.app.tn.eda.processors;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.app.tn.data.TuplePair;
import com.wm.data.IData;

@Weave(type = MatchType.Interface)
public abstract class Processor {

	public void process(TuplePair<IData, String> var1) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","TradingNetworks","Processor",getClass().getSimpleName(),"process");
		Weaver.callOriginal();
	}
}
