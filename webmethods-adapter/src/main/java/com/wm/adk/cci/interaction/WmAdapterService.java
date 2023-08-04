package com.wm.adk.cci.interaction;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.adk.cci.record.WmRecord;
import com.wm.adk.connection.WmManagedConnection;

@Weave(type = MatchType.BaseClass)
public abstract class WmAdapterService {

	@Trace
	public WmRecord execute(WmManagedConnection var1, WmRecord var2) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","Adapter",getClass().getSimpleName(),"execute");
		return Weaver.callOriginal();
	}
	
	@Trace
	public boolean execute(WmManagedConnection connection, WmRecord input, WmRecord output) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","Adapter",getClass().getSimpleName(),"execute");
		return Weaver.callOriginal();
	}
	
}
