package com.wm.adapter.sap;

import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.webmethods.sap.montior.WMSAPUtils;
import com.sap.conn.idoc.jco.JCoIDocServer;
import com.sap.conn.jco.server.JCoServerState;

@Weave
public abstract class SAPListener {

	private JCoIDocServer _jcoServer = Weaver.callOriginal();

	public abstract String getAlias();
	public abstract SAPSystem getSAPSystem();
	public abstract String getGatewayHost();
	public abstract String getGatewayService();
	public abstract String getProgID();
	public abstract String getRepositoryAlias();
	public abstract String getStateAsString();
	public abstract int getActiveThreadCount();
	public abstract int getBusyThreadCount();
	public abstract int getThreadCount() ;

	@Trace
	public synchronized void startListener() {
		JCoServerState serverState = this._jcoServer.getState();
		/*
		 * Record start only if the listener is not already running.
		 * Record the start after the method is called
		 */
		boolean record = serverState != JCoServerState.STARTED;
		Weaver.callOriginal();
		if(record) {
			// re-check server state to ensure it started properly
			serverState = this._jcoServer.getState();

			if(serverState == JCoServerState.STARTED) {
				NewRelic.recordMetric(WMSAPUtils.SAPLISTENER_START_METRIC_PREFIX+getAlias(), 1.0F);
				HashMap<String, Object> attributes = new HashMap<>();
				WMSAPUtils.addSAPListener(attributes, this);
				NewRelic.getAgent().getInsights().recordCustomEvent("WM_SAPListener_Start", attributes);
			}
		}
	}
	
	@Trace
	protected void stopListener() {
		JCoServerState serverState = this._jcoServer.getState();
		/*
		 * Record stop only if the listener is already running.
		 * Record the stop after the method is called
		 */
		boolean record = serverState != JCoServerState.STARTED;
		Weaver.callOriginal();
		if(record) {
			// re-check server state to ensure it started properly
			serverState = this._jcoServer.getState();

			if(serverState != JCoServerState.STARTED) {
				NewRelic.recordMetric(WMSAPUtils.SAPLISTENER_STOP_METRIC_PREFIX+getAlias(), 1.0F);
				HashMap<String, Object> attributes = new HashMap<>();
				WMSAPUtils.addSAPListener(attributes, this);
				NewRelic.getAgent().getInsights().recordCustomEvent("WM_SAPListener_Stop", attributes);
			}
		}
		
	}
}
