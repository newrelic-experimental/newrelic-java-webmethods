package com.wm.app.b2b.server.dispatcher;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.app.store.IMessageCB;
import com.wm.app.store.IMessageState;

@Weave
public abstract class MessageRouter {

	@Trace
	public boolean handleMessage(Object msg, IMessageState msgState, IMessageCB msgStore) {
		return Weaver.callOriginal();
	}
}
