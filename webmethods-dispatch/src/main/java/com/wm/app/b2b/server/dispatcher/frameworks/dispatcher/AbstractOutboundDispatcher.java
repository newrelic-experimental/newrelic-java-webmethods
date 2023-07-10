package com.wm.app.b2b.server.dispatcher.frameworks.dispatcher;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.dispatch.WmMessageHeaders;
import com.wm.app.b2b.server.InvokeState;
import com.wm.app.b2b.server.dispatcher.DispatcherMessage;
import com.wm.data.IData;
import com.wm.lang.ns.NSName;

@Weave(type = MatchType.BaseClass)
public abstract class AbstractOutboundDispatcher {
	
	
	public DispatcherMessage populateControlData(IData requestDocumentEnvelope, NSName pubRecordName, IData msg, String destId, String cmd, boolean localPublish) {
		DispatcherMessage resultMsg = Weaver.callOriginal();
		WmMessageHeaders headers = new WmMessageHeaders(resultMsg);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(headers);
		return resultMsg;
	}
	
	public void publish(NSName pubRecordName, String msgId, IData msg, String destId, boolean localPublish, boolean publishOnSuccess)  {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("Destination", destId);
		traced.addCustomAttribute("PublishRecord", pubRecordName.getFullName());
		traced.addCustomAttribute("MessageID", msgId);
		Weaver.callOriginal();
	}
	
	public void reply(IData requestDocumentEnvelope, NSName replyDocumentName, IData replyDocument, boolean replyOnSuccess) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("ReplyDocumentName", replyDocumentName.getFullName());
		Weaver.callOriginal();
	}
	
	public IData publishAndWait(NSName pubRecordName, String msgId, IData msg, String destId, NSName replyMsgName, long wait, boolean localPublish) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("Destination", destId);
		traced.addCustomAttribute("PublishRecord", pubRecordName.getFullName());
		traced.addCustomAttribute("MessageID", msgId);
		return Weaver.callOriginal();
	}
	
	public void publishOnSuccess(InvokeState state) {
		
	}
}
