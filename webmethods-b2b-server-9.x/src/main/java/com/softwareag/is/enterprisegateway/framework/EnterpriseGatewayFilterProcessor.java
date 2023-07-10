package com.softwareag.is.enterprisegateway.framework;

import java.util.HashMap;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.WebMethodsUtils;
import com.softwareag.is.enterprisegateway.alert.EnterpriseGatewayAlert;
import com.wm.app.b2b.server.HTTPState;
import com.wm.net.HttpHeader;

@Weave(type = MatchType.Interface)
public abstract class EnterpriseGatewayFilterProcessor {

	@Trace(dispatcher = true)
	public void processFilter(HTTPState var1, EnterpriseGatewayFilter var2, EnterpriseGatewayAlert var3, HttpHeader var4) {
		HashMap<String, Object> attributes = new HashMap<>();
		WebMethodsUtils.addValue(attributes, "ReqUrl", var1.getReqUrl());
		WebMethodsUtils.addValue(attributes, "RequestURL", var1.getRequestUrl());
		Weaver.callOriginal();
	}
}
