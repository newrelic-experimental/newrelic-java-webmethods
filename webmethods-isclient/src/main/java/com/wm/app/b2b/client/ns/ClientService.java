package com.wm.app.b2b.client.ns;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.isclient.WebMethodsUtils;
import com.wm.lang.ns.NSName;
import com.wm.lang.ns.NSPackage;
import com.wm.lang.ns.NSService;
import com.wm.lang.ns.NSServiceType;
import com.wm.lang.ns.Namespace;
import com.wm.util.Values;

@Weave
abstract class ClientService extends NSService {
	
	private ClientService(Namespace ns, NSPackage pkg, NSName name, NSServiceType svcType,
			ContextWrapper aContextWrapper) {
		super(ns, pkg, name, svcType);
	}
	
	protected ClientService(Namespace ns, Values vals, ContextWrapper aContextWrapper) {
		super(ns, vals);
	}

	@Override
	@Trace(dispatcher = true)
	public Values invoke(Values var1) throws Exception {
		NSName n = getNSName();
		String name = n!= null ? n.toString() : null;
		if(name != null) {
			String[] metricName = WebMethodsUtils.getMetricName("Custom","WebMethods","ClientService",name,"invoke");
			NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
			String[] txnName = WebMethodsUtils.getMetricName("ClientService",name);
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_LOW, false, "ClientService", txnName);
		}
		return Weaver.callOriginal();
	}

}
