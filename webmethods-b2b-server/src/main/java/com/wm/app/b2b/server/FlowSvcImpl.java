package com.wm.app.b2b.server;

import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.WebMethodsUtils;
import com.wm.data.IData;
import com.wm.lang.flow.FlowRoot;
import com.wm.lang.ns.NSName;
import com.wm.lang.ns.NSServiceType;
import com.wm.util.Values;

@Weave(type = MatchType.BaseClass)
public abstract class FlowSvcImpl extends BaseService {

	public FlowSvcImpl(Package pkg, NSName name, Values vals) {
		super(pkg, name, NSServiceType.create("flow", "unknown"));
	}
	
	public FlowSvcImpl(Values vals) {
		super(vals);
	}
	
	public abstract FlowRoot getFlowRoot();
	
	public abstract String getParentRAD();
	
	public abstract String getParentWSD();
	
	@Trace(dispatcher = true)
	public Values baseInvoke(Values var1) {
		NSName n = getNSName();
		String name = n!= null ? n.toString() : null;
		if(name != null) {
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","FlowSvcImpl",name,"baseInvoke");
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "FlowService", "Flow",name);
		}
		return Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public IData baseInvoke(IData var1) {
		NSName n = getNSName();
		String name = n!= null ? n.toString() : null;
		if(name != null) {
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","FlowSvcImpl",name,"baseInvoke");
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "FlowService", "Flow",name);
		}
		return Weaver.callOriginal();
	}

}
