package com.wm.app.b2b.server.dispatcher.frameworks.publisher;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.app.b2b.server.dispatcher.DispatcherMessage;

@Weave(type = MatchType.Interface)
public abstract class IAsyncPublisher {

	@Trace
	public boolean publish(DispatcherMessage var1) {
		return Weaver.callOriginal();
	}

	@Trace
	public boolean publish(DispatcherMessage[] var1) {
		return Weaver.callOriginal();
	}

}
