package com.softwareag.app.tn.eda.processors;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;
import com.wm.app.tn.data.TuplePair;
import com.wm.data.IData;

@Weave
public abstract class ERFStub {

	@Trace(dispatcher = true)
	public void process(TuplePair<IData, String> event) {
		String eType = TNUtils.getEventType(event);
		if(eType != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttribute("EventType", eType);
		}
		Weaver.callOriginal();
	}
}
