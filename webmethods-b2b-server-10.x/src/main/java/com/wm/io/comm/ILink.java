package com.wm.io.comm;

import java.util.HashMap;
import java.util.Properties;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.PropertiesWrapper;
import com.newrelic.instrumentation.webmethods.b2bserver.WebMethodsUtils;
import com.wm.data.IData;

@Weave(type = MatchType.Interface)
public abstract class ILink {

	@Trace
	public void send(String var1, String var2, Properties var3, IData var4) {
		PropertiesWrapper wrapper = new PropertiesWrapper(var3);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(wrapper);
		Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public IMessage receive(boolean var1) {
		IMessage message = Weaver.callOriginal();
		HashMap<String, Object> attributes = new HashMap<>();
		WebMethodsUtils.addIMessage(attributes, message);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);

		if(message != null) {
			PropertiesWrapper wrapper = new PropertiesWrapper(message.getProperties());
			NewRelic.getAgent().getTransaction().acceptDistributedTraceHeaders(TransportType.Other, wrapper);
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, true, "IMessage", "IMessage","Receive",message.getCommand());
		} else {
			NewRelic.getAgent().getTransaction().ignore();
		}
		return message;
	}

}
