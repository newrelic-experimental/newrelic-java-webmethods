package com.wm.app.b2b.server.scheduler;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class ScheduleDB {
	
	@Weave
	private static  class ScheduledService {
		
		ScheduledTask task = Weaver.callOriginal();
		
		@Trace(dispatcher = true)
		public void run() {
			if(task != null) {
				String service = task.getService();
				if(service != null) {
					NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "ScheduledTask", "WebMethods","ScheduledTask", service);
					NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","ScheduledTask", service);
				}
			}
			Weaver.callOriginal();
		}
	}
} 
