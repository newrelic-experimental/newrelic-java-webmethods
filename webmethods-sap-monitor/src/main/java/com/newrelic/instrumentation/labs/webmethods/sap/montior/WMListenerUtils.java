package com.newrelic.instrumentation.labs.webmethods.sap.montior;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import com.newrelic.api.agent.Insights;
import com.newrelic.api.agent.NewRelic;

public class WMListenerUtils {

	private static ScheduledFuture<?> currentExecutorFuture = null;

	
	private static long frequency = 1;
	
	public static boolean enabled = true;
	
	public static boolean listenerInitialized = false;
	
	static {
		if(!listenerInitialized && enabled) {
			initializeListener();
		}
	}
	
	public static void setEnabled(boolean b) {
		if (b != enabled) {
			// check current state and apply any necessary changes
			if (enabled) {
				// Monitor is running and we need to shut it down
				if(currentExecutorFuture != null) {
					currentExecutorFuture.cancel(true);
					HashMap<String, Object> attributes = new HashMap<>();
					Date now = new Date();
					attributes.put("Shutdown Time", now.toString());
					Insights insights = NewRelic.getAgent().getInsights();
					if(insights != null) {
						NewRelic.getAgent().getInsights().recordCustomEvent("SAPListenerMonitor_Stop", attributes);
						NewRelic.getAgent().getLogger().log(Level.FINE, "Sent listener start event to NR: {0}", attributes);
					} else {
						NewRelic.getAgent().getLogger().log(Level.FINE, "Could not send start event because Insights is null");
						DelaySend delaySend = new DelaySend("SAPListenerMonitor_Start",attributes);
						Executors.newSingleThreadExecutor().submit(delaySend);
					}
					NewRelic.getAgent().getLogger().log(Level.FINE, "Sent listener stop event to NR: {0}", attributes);
				}
				currentExecutorFuture = null;
			} else {
				// Monitor is not running and we need to start it
				initializeListener();
			} 
			// set new value
			enabled = b;
		}
	}
	
	public static void rescheduleListener(long freq) {
		
		if(!enabled) return;
		
		if (freq != frequency) {
			if(currentExecutorFuture != null) currentExecutorFuture.cancel(true);
			frequency = freq;
			HashMap<String, Object> attributes = new HashMap<>();
			Date now = new Date();
			attributes.put("Start Time", now.toString());
			attributes.put("FrequencyInMinutes", frequency);
			
			Insights insights = NewRelic.getAgent().getInsights();
			if(insights != null) {
				NewRelic.getAgent().getInsights().recordCustomEvent("SAPListenerMonitor_Start", attributes);
				NewRelic.getAgent().getLogger().log(Level.FINE, "Sent listener start event to NR: {0}", attributes);
			} else {
				NewRelic.getAgent().getLogger().log(Level.FINE, "Could not send start event because Insights is null");
				DelaySend delaySend = new DelaySend("SAPListenerMonitor_Stop",attributes);
				Executors.newSingleThreadExecutor().submit(delaySend);
			}
			NewRelic.getAgent().getLogger().log(Level.FINE, "Sent listener start event to NR: {0}", attributes);
			currentExecutorFuture = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(WMSAPListenerMonitor.INSTANCE, frequency, frequency, TimeUnit.MINUTES);
			if(!listenerInitialized) listenerInitialized = true;
		} else {
			if(!listenerInitialized) {
				initializeListener();
			}
		}
	}
	
	public static void initializeListener() {
		if (enabled && currentExecutorFuture == null) {
			NewRelic.getAgent().getLogger().log(Level.FINE, "Submitting SAP Monitor to ScheduledExecutor");
			currentExecutorFuture = Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(WMSAPListenerMonitor.INSTANCE, frequency, frequency, TimeUnit.MINUTES);
			listenerInitialized = true;
			HashMap<String, Object> attributes = new HashMap<>();
			Date now = new Date();
			attributes.put("Start Time", now.toString());
			attributes.put("FrequencyInMinutes", frequency);
			Insights insights = NewRelic.getAgent().getInsights();
			if(insights != null) {
				NewRelic.getAgent().getInsights().recordCustomEvent("SAPListenerMonitor_Start", attributes);
				NewRelic.getAgent().getLogger().log(Level.FINE, "Sent listener start event to NR: {0}", attributes);
			} else {
				NewRelic.getAgent().getLogger().log(Level.FINE, "Could not send start event because Insights is null");
				DelaySend delaySend = new DelaySend("SAPListenerMonitor_Start",attributes);
				Executors.newSingleThreadExecutor().submit(delaySend);
			}
		}
	}


	private static class DelaySend implements Runnable {
		
		private HashMap<String, Object> attributes = null;
		private String eventName = null;
		
		private DelaySend(String event, HashMap<String, Object> attr) {
			attributes = attr;
			eventName = event;
		}

		@Override
		public void run() {
			boolean done = false;
			while (!done) {
				Insights insights = NewRelic.getAgent().getInsights();
				if (insights != null) {
					NewRelic.getAgent().getInsights().recordCustomEvent(eventName, attributes);
					NewRelic.getAgent().getLogger().log(Level.FINE, "Sent listener start event to NR: {0}", attributes);
					done = true;
				}
				if(!done) {
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
					}
				}
			}
			
			
		}
		
	}
}
