package com.wm.app.b2b.server;

import java.io.IOException;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.BaseClass)
public abstract class HTTPDispatch {

	@Trace(dispatcher = true)
	protected boolean handleRequest(boolean defAllowed) throws IOException, AccessException {
		NewRelic.getAgent().getTransaction().convertToWebTransaction();
		try {
			return Weaver.callOriginal();
		} catch (Exception e) {
			if (e instanceof IOException || e instanceof AccessException) {
				NewRelic.noticeError(e);
			}
			throw e;
		}
	}
}
