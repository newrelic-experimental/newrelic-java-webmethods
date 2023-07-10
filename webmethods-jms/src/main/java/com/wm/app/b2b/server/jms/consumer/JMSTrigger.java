package com.wm.app.b2b.server.jms.consumer;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.TransportType;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.webmethods.jms.WMJMSHeaders;
import com.wm.app.b2b.server.jms.message.JMSMessage;
import com.wm.data.IData;
import com.wm.lang.ns.NSName;

@SuppressWarnings("unused")
@Weave(type = MatchType.BaseClass)
public abstract class JMSTrigger {

	public abstract String getName();

	@Trace(dispatcher = true)
	public void processMessage(JMSMessage[] messages) {
		Weaver.callOriginal();
	}
	
	private void invokeService(NSName svc, IData inputs) {
		NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "WebMethods-JMS", "WebMethods","JMS","Service",getName());

		Weaver.callOriginal();
	}

	private void processBatchOfMessages(JMSMessage[] messages)  {
		Transaction txn = NewRelic.getAgent().getTransaction();
		if(messages != null && messages.length >= 1) {
			WMJMSHeaders headers = new WMJMSHeaders(messages[0]);
			txn.acceptDistributedTraceHeaders(TransportType.JMS, headers);
		}
		Weaver.callOriginal();
	}

	private void processSOAPMessage(JMSMessage[] jmsMessages) {
		Transaction txn = NewRelic.getAgent().getTransaction();
		if(jmsMessages != null && jmsMessages.length == 1) {
			JMSMessage msg = jmsMessages[0];
			WMJMSHeaders headers = new WMJMSHeaders(msg);
			txn.acceptDistributedTraceHeaders(TransportType.JMS, headers);
		}
		txn.setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "WebMethods-JMS", "WebMethods","JMS","Trigger","SOAP",getName());
		Weaver.callOriginal();
	}

	private void processSingleMessage(JMSMessage message) {
		Transaction txn = NewRelic.getAgent().getTransaction();
		WMJMSHeaders headers = new WMJMSHeaders(message);
		txn.acceptDistributedTraceHeaders(TransportType.JMS, headers);
		Weaver.callOriginal();
	}
	
	private void processSingleXORMessage(JMSMessage message)  {
		Transaction txn = NewRelic.getAgent().getTransaction();
		WMJMSHeaders headers = new WMJMSHeaders(message);
		txn.acceptDistributedTraceHeaders(TransportType.JMS, headers);
		Weaver.callOriginal();
	}
	
	private void processSingleANDMessage(JMSMessage message)  {
		Transaction txn = NewRelic.getAgent().getTransaction();
		WMJMSHeaders headers = new WMJMSHeaders(message);
		txn.acceptDistributedTraceHeaders(TransportType.JMS, headers);
		Weaver.callOriginal();
	}
}
