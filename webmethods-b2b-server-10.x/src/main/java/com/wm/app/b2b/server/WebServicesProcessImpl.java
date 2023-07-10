package com.wm.app.b2b.server;

import java.io.IOException;
import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.WebMethodsUtils;
import com.wm.lang.ns.NSName;
import com.wm.lang.websvc.WSOperation;

@Weave
public abstract class WebServicesProcessImpl {
	
	String soapaction = Weaver.callOriginal();
	String interfaceName = Weaver.callOriginal();
	String wsdName = Weaver.callOriginal();

	WSOperation operation = Weaver.callOriginal();
	String opName = Weaver.callOriginal();
	
	@Trace(dispatcher = true)
	public boolean process(ProtocolState _state) throws IOException, AccessException {
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
