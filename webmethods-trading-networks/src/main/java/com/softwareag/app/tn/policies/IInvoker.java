package com.softwareag.app.tn.policies;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class IInvoker<R, A> {

	@Trace
	public R invoke(A var1) {
		return Weaver.callOriginal();
	}
}
