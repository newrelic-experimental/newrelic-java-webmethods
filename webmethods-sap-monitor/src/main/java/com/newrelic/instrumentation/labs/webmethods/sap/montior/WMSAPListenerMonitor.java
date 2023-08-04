package com.newrelic.instrumentation.labs.webmethods.sap.montior;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.newrelic.api.agent.NewRelic;
import com.wm.adapter.sap.SAPAdapterManager;
import com.wm.adapter.sap.SAPAdapterManager.PoolInfo;
import com.wm.adapter.sap.SAPListener;
import com.wm.adapter.sap.SAPSystem;
import com.wm.adapter.sap.SAPSystem.LockedConnectionInfo;

public class WMSAPListenerMonitor implements Runnable {
	
	public static WMSAPListenerMonitor INSTANCE;
	private static int collectionFailed = 0;
	private static int classNotFound = 0;
	private static int MAX_FAILURES = 5;
	
	static {
		INSTANCE = new WMSAPListenerMonitor();
	}
	
	private WMSAPListenerMonitor() {
		
	}
	
	private static void addAttribute(Map<String,Object> attributes, String key, Object value) {
		if(attributes != null && key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}
	
	@Override
	public void run() {
		NewRelic.getAgent().getLogger().log(Level.FINE, "Running WebMethods SAPListener monitor");
		try {
			SAPAdapterManager apdapterMgr = SAPAdapterManager.getInstance();
			SAPListener[] listeners = apdapterMgr.getSAPListener();
			for(SAPListener listener : listeners) {
				HashMap<String, Object> attributes = new HashMap<>();
				addAttribute(attributes, "Alias", listener.getAlias());
				addAttribute(attributes, "GatewayService", listener.getGatewayService());
				addAttribute(attributes, "State", listener.getStateAsString());
				addAttribute(attributes, "ActiveThreads", listener.getActiveThreadCount());
				addAttribute(attributes, "BusyThreads", listener.getBusyThreadCount());
				addAttribute(attributes, "ThreadCount", listener.getThreadCount());
				NewRelic.getAgent().getInsights().recordCustomEvent("WM_SAP_LISTENER", attributes);
			}
			
			SAPSystem[] systems = apdapterMgr.getSAPSystems();
			for(SAPSystem system : systems) {
				HashMap<String, Object> attributes = new HashMap<>();
				addAttribute(attributes, "Alias", system.getAlias());
				addAttribute(attributes, "ConnNodeName", system.getConnNodeName());
				addAttribute(attributes, "Client", system.getClient());
				addAttribute(attributes, "GatewayService", system.getGatewayService());
				addAttribute(attributes, "ProgramID", system.getProgramID());
				addAttribute(attributes, "Router", system.getRouterString());
				addAttribute(attributes, "LockedConnections", system.getLockedConnectionCount());
				addAttribute(attributes, "PoolCount", system.getPoolCount());
				NewRelic.getAgent().getInsights().recordCustomEvent("WM_SAP_SYSTEM", attributes);
				
				PoolInfo[] poolInfos = system.getPoolInfos();
				for(PoolInfo info : poolInfos) {
					attributes.clear();
					addAttribute(attributes, "PoolName", info.getName());
					addAttribute(attributes, "CurrentSize", info.getCurrentSize());
					addAttribute(attributes, "CurrentlyUsed", info.getCurrentUsed());
					addAttribute(attributes, "PeakSize", info.getPeakSize());
					NewRelic.getAgent().getInsights().recordCustomEvent("WM_SAP_POOL", attributes);
				}
				
				
				LockedConnectionInfo[] lockedConns = system.getLockedConnectionInfo();
				for(LockedConnectionInfo locked : lockedConns) {
					attributes.clear();
					addAttribute(attributes, "PoolName", locked.getPoolName());
					addAttribute(attributes, "Host", locked.getHost());
					addAttribute(attributes, "Client", locked.getClient());
					addAttribute(attributes, "ConversationID", locked.getConversationID());
					addAttribute(attributes, "SessionID", locked.getSessionID());
					NewRelic.getAgent().getInsights().recordCustomEvent("WM_SAP_LOCKEDCONNECTION", attributes);
				}
			}
		} catch (Throwable e) {
			// disable monitor if problems are occurring
			if(e instanceof ClassNotFoundException || e instanceof NoClassDefFoundError) {
				NewRelic.getAgent().getLogger().log(Level.FINE, "Failed to load SAPAdapterManager");
				// Keep trying for 5 times in case the class has not been loaded yet
				classNotFound++;
				if(classNotFound == MAX_FAILURES) {
					// Assume WebMethods SAP Adapter is not present
					NewRelic.getAgent().getLogger().log(Level.FINE,e, "Disabling WebMethods SAP Monitor as it does not seem to present");
					WMListenerUtils.setEnabled(false);
					HashMap<String, Object> attributes = new HashMap<>();
					attributes.put("Shutdown Type", "Failed to find SAPAdapterManager");
					Date now = new Date();
					attributes.put("Failure Time",now.toString());
					NewRelic.getAgent().getInsights().recordCustomEvent("SAPListenerMonitor_Error", attributes);
					NewRelic.getAgent().getLogger().log(Level.FINE, "Sent listener error event to NR: {0}", attributes);

				}
			} else {
				NewRelic.getAgent().getLogger().log(Level.FINE, e,"Unable to run WebMethods SAP Listener Monitor due to error");
				collectionFailed++;
				if(collectionFailed == MAX_FAILURES) {
					NewRelic.getAgent().getLogger().log(Level.FINE,e, "Disabling WebMethods SAP Monitor as it has failed {0} times and has reached the max number of failures {1}",collectionFailed,MAX_FAILURES);
					HashMap<String, Object> attributes = new HashMap<>();
					attributes.put("Shutdown Type", "Exceeded Max Errors");
					Date now = new Date();
					attributes.put("Failure Time",now.toString());
					NewRelic.getAgent().getInsights().recordCustomEvent("SAPListenerMonitor_Error", attributes);
					NewRelic.getAgent().getLogger().log(Level.FINE, "Sent listener error event to NR: {0}", attributes);
					WMListenerUtils.setEnabled(false);
				}
			}
		}
		
	}

}
