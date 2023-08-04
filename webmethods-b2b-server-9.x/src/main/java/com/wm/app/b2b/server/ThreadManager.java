package com.wm.app.b2b.server;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.WMHeaders;

@Weave
public abstract class ThreadManager {
	
	public static String runTarget(Runnable target) {
		if(target instanceof ServiceThread) {
			WMHeaders headers = new WMHeaders();
			NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(headers);
			((ServiceThread)target).headers = headers;
		}
		return Weaver.callOriginal();
	}

}
