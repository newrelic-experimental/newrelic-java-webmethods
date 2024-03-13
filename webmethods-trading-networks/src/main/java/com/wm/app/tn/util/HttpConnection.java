package com.wm.app.tn.util;

import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;
import com.wm.app.tn.data.ServerConfig;
import com.wm.app.tn.util.LoadAs;
import com.wm.net.HTTPMethod;

@Weave(type = MatchType.BaseClass)
public abstract class HttpConnection  {

	public abstract LoadAs getLoadAs();
	public abstract HTTPMethod getMethod();
	public abstract String getURL();
	public abstract String getResponseContentType();
	
	@NewField
	protected String host;
	
	@NewField
	protected Integer port;
	
	public HttpConnection(ServerConfig config) {
		host = config.getHost();
		port = config.getPort();
	}
	
	@Trace(dispatcher = true)
	public void invoke() {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		
		traced.setMetricName("Custom","WebMethods","HttpConnection",getClass().getSimpleName(),"invoke");
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "LoadAs", getLoadAs());
		TNUtils.reportAttribute(attributes, "HttpMethod", getMethod());
		TNUtils.reportAttribute(attributes, "URL", getURL());
		TNUtils.reportAttribute(attributes, "ResponseContentType", getResponseContentType());
		TNUtils.reportAttribute(attributes, "Host", host);
		TNUtils.reportAttribute(attributes, "Port", port);
		
		traced.addCustomAttributes(attributes);
		Weaver.callOriginal();
	}
}
