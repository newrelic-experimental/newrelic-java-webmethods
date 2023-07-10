package com.wm.app.b2b.server.dispatcher;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.app.b2b.server.dispatcher.comms.BrokerBaseTransport;

@Weave(type = MatchType.BaseClass)
public abstract class Consumer {

	public abstract String getName();
	
	@Trace(dispatcher = true)
	public int doWork() {
		NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "WebMethods-Consumer", "Consumer",getName(),"DoWork");
		int numberOfEvents = Weaver.callOriginal();
		if(numberOfEvents < 1) {
			NewRelic.getAgent().getTransaction().ignore();
		}
		return numberOfEvents;
	}
	
	@Trace
	protected DispatcherMessage[] receiveEvents(BrokerBaseTransport baseTransport, int noOfEventsToRead, int timeBetweenEvents) {
		return Weaver.callOriginal();
	}
}
