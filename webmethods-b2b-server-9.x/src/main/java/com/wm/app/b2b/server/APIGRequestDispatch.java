package com.wm.app.b2b.server;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.softwareag.is.apigateway.APIGState;

@Weave
public abstract class APIGRequestDispatch {

	@Trace
	public APIGState forwardToInternalServer(APIGState pState, String regAlias) {
		return Weaver.callOriginal();
	}
	
	@Trace
	protected boolean handleMediatorRequest(boolean defallowed) {
		return Weaver.callOriginal();
	}
}
