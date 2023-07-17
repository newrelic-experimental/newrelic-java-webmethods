package com.wm.app.b2b.server;

import java.io.IOException;
import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.HTTPHeaderWrapper;
import com.newrelic.instrumentation.webmethods.b2bserver.WebMethodsUtils;
import com.wm.lang.ns.NSName;
import com.wm.lang.websvc.WSOperation;
import com.wm.net.HttpHeader;

@Weave
public abstract class WebServicesProcessImpl {
	
	String soapaction = Weaver.callOriginal();
	String interfaceName = Weaver.callOriginal();
	String wsdName = Weaver.callOriginal();

	WSOperation operation = Weaver.callOriginal();
	String opName = Weaver.callOriginal();
	
	@Trace(dispatcher = true)
	public boolean process(ProtocolState _state) throws IOException, AccessException {
		String requestURL = _state.getRequestUrl();
		if(requestURL != null && !requestURL.isEmpty()) {
			NewRelic.getAgent().getTracedMethod().addCustomAttribute("RequestURL", requestURL);
		}
		Transaction transaction = NewRelic.getAgent().getTransaction();
		if(!transaction.isWebTransaction()) {
			transaction.convertToWebTransaction();
		}
		HttpHeader header = _state.getRequestHeader();
		if(header != null) {
			HTTPHeaderWrapper wrapper = new HTTPHeaderWrapper(header);
			transaction.acceptDistributedTraceHeaders(TransportType.HTTP, wrapper);
		}
		try {
			boolean b = Weaver.callOriginal();
			String[] metricName = WebMethodsUtils.getMetricName("Custom","WebServicesProcessImpl","process",interfaceName,opName);
			NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
			HashMap<String, Object> attributes = new HashMap<>();
			WebMethodsUtils.addValue(attributes, "wsName", wsdName);
			WebMethodsUtils.addValue(attributes, "SOAPAction", soapaction);
			NSName sName = operation.getServiceName();
			if(sName != null) {
				WebMethodsUtils.addValue(attributes, "Service", sName.getFullName());
			}
			return b;
		} catch (Exception e) {
			if (e instanceof IOException || e instanceof AccessException) {
				NewRelic.noticeError(e);
			}
			throw e;
		}
	}
}
