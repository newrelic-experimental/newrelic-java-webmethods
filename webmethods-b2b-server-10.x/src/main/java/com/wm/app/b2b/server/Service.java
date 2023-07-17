package com.wm.app.b2b.server;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.data.IData;
import com.wm.lang.ns.NSName;
import com.wm.util.Values;

@Weave
public abstract class Service {

	@Trace
	public static IData doInvoke(NSName name, Session session, IData input) {
		return Weaver.callOriginal();
	}
	
	@Trace
	public static Values doInvoke(NSName name, Session session, Values input) {
		return Weaver.callOriginal();
	}
	
	@Trace
	public static ServiceThread doThreadInvoke(NSName svc, Session session, IData input, long time) {
		return Weaver.callOriginal();
	}
}
