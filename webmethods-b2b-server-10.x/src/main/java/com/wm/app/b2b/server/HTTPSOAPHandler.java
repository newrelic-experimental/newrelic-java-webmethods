package com.wm.app.b2b.server;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.HTTPHeaderWrapper;
import com.wm.driver.comm.b2b.WmMessage;
import com.wm.lang.ns.NSService;
import com.wm.net.HttpHeader;
import com.wm.util.Values;

@Weave
public abstract class HTTPSOAPHandler {

	@Trace(dispatcher = true)
	private Values _process(String reqUrl, ProtocolState state, WmMessage requestMsg, ContentHandler cHandler) {
		Transaction transaction = NewRelic.getAgent().getTransaction();
		if(!transaction.isWebTransaction()) {
			transaction.convertToWebTransaction();
		}
		InvokeState invokeState = null;
		if(state != null) {
			String requestURL = state.getRequestUrl();
			if(requestURL != null && !requestURL.isEmpty()) {
				NewRelic.getAgent().getTracedMethod().addCustomAttribute("RequestURL", requestURL);
			}
			invokeState = state.getInvokeState();
			HttpHeader header = state.getRequestHeader();
			if(header != null) {
				HTTPHeaderWrapper wrapper = new HTTPHeaderWrapper(header);
				transaction.acceptDistributedTraceHeaders(TransportType.HTTP, wrapper);
			}
		} else {
			invokeState = InvokeState.getCurrentState();
		}
		if(invokeState != null) {
			NSService service = invokeState.getService();
			transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "HTTPSOAP", "SOAP",service.getNSName().getFullName());
		}
		return Weaver.callOriginal();
	}
}
