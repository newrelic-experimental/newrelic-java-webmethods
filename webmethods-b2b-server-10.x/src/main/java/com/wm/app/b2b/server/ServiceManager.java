package com.wm.app.b2b.server;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.WebMethodsUtils;
import com.wm.data.IData;
import com.wm.lang.ns.NSName;

@Weave
public class ServiceManager {

	@Trace(dispatcher = true)
	public static IData invoke(BaseService se, IData input, boolean inReinvoke)  {
		NSName serviceName = se.getNSName();
		boolean ignore = WebMethodsUtils.servicesToIgnore.contains(serviceName.getFullName());
		if(ignore) {
			NewRelic.getAgent().getTransaction().ignore();
		} else {
			String name = serviceName != null ? serviceName.getFullName() : "UnknownService";

			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "ServiceManager", "ServiceManager", "Invoke", name);
		}

		return Weaver.callOriginal();
	}
}
