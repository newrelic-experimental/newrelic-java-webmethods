package com.wm.adk.cci.interaction;

import javax.resource.cci.InteractionSpec;
import javax.resource.cci.Record;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.BaseClass)
public abstract class WmInteraction {

	@Trace
	public Record execute(final InteractionSpec spec, final Record input) {
		return Weaver.callOriginal();
	}

	@Trace
	public boolean execute(final InteractionSpec spec, final Record input, final Record output) {
		return Weaver.callOriginal();
	}
}
