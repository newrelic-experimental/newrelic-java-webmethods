package com.wm.app.b2b.server;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.BaseClass)
public abstract class HTTPDispatch {

	@Trace(dispatcher = true)
	protected boolean handleRequest(boolean defAllowed) {
		NewRelic.getAgent().getTransaction().convertToWebTransaction();
		return Weaver.callOriginal();
	}
}
