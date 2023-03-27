package com.wm.app.b2b.server;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.softwareag.is.apigateway.APIGState;

@Weave
public abstract class APIGRequestDispatch {

	@Trace
	public APIGState forwardToInternalServer(APIGState apigState, int internalPortNumber) {
		return Weaver.callOriginal();
	}
	
	@Trace
	public APIGState handleMediatorRequest(ProtocolState protocolState) {
		return Weaver.callOriginal();
	}
}
