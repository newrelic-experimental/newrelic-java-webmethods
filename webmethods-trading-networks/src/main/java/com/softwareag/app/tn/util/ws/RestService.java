package com.softwareag.app.tn.util.ws;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.data.IData;

@Weave
public abstract class RestService {

	@Trace
	public Object invoke() {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName("Custom","WebMethods","IWebService",getClass().getSimpleName(),"invoke");
		traced.addCustomAttribute("WithInputData", false);
		return Weaver.callOriginal();
	}

	@Trace
	public Object invoke(IData var1) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName("Custom","WebMethods","IWebService",getClass().getSimpleName(),"invoke");
		traced.addCustomAttribute("WithInputData", true);
		return Weaver.callOriginal();
	}

}
