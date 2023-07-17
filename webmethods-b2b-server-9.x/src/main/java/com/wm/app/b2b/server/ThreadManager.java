package com.wm.app.b2b.server;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class ThreadManager {
	
	public static String runTarget(Runnable target) {
		if(target instanceof ServiceThread) {
			((ServiceThread)target).token = NewRelic.getAgent().getTransaction().getToken();
		}
		return Weaver.callOriginal();
	}

}
