package com.wm.app.b2b.server.dispatcher;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.dispatch.WmMessageHeaders;
import com.wm.data.IData;
import com.wm.lang.ns.NSName;

@Weave(type = MatchType.BaseClass)
public abstract class Dispatcher {

	
	@SuppressWarnings("unused")
	private DispatcherMessage _populateControlData(IData requestDocumentEnvelope, NSName pubRecordName, String msgId,
			IData msg, String destId, String cmd, boolean localPublish) {
		DispatcherMessage resultMsg = Weaver.callOriginal();
		WmMessageHeaders headers = new WmMessageHeaders(resultMsg);
		NewRelic.getAgent().getTransaction().insertDistributedTraceHeaders(headers);
		return resultMsg;
	}
	
	@Trace
	public void publish(NSName pubRecordName, String msgId, IData msg, String destId, boolean localPublish, boolean publishOnSuccess) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("Destination", destId);
		traced.addCustomAttribute("PublishRecord", pubRecordName.getFullName());
		traced.addCustomAttribute("MessageID", msgId);
		Weaver.callOriginal();
	}
	
	@Trace
	public IData publishAndWait(NSName pubRecordName, String msgId, IData msg, String destId, NSName replyMsgName, long wait, boolean localPublish) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("Destination", destId);
		traced.addCustomAttribute("PublishRecord", pubRecordName.getFullName());
		traced.addCustomAttribute("MessageID", msgId);
		return Weaver.callOriginal();
	}
	
	@Trace
	public void reply(IData requestDocumentEnvelope, NSName replyDocumentName, IData replyDocument, boolean replyOnSuccess) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("ReplyDocumentName", replyDocumentName.getFullName());
		Weaver.callOriginal();
	}
	
	@Trace
	public IData waitForReply(String tag) {
		return Weaver.callOriginal();
	}
}
