package com.wm.app.b2b.server;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.lang.ns.NSName;

@Weave(type = MatchType.BaseClass)
public abstract class InvokeHandler {

	protected abstract String getDirective(ProtocolState state);
	
	@Trace(dispatcher = true)
	protected boolean _process(ProtocolState var1, ContentHandler var2, NSName var3) {
		String requestURL = var1.getRequestUrl();
		if(requestURL != null && !requestURL.isEmpty()) {
			NewRelic.getAgent().getTracedMethod().addCustomAttribute("RequestURL", requestURL);
		}
		Transaction transaction = NewRelic.getAgent().getTransaction();
		if(!transaction.isWebTransaction()) {
			transaction.convertToWebTransaction();
		}
		transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "HttpInvoke", "InvokeHandler",var3.getFullName());
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName("Custom","WebMethods","InvokeHandler",var3.getFullName());
		traced.addCustomAttribute("Directive", getDirective(var1));
		return Weaver.callOriginal();
	}
}
