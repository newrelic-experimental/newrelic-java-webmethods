package com.newrelic.instrumentation.labs.webmethods.sap.montior;

import java.util.Map;

import com.wm.adapter.sap.SAPListener;
import com.wm.adapter.sap.SAPSystem;

public class WMSAPUtils {
	
	public static final String SAPLISTENER_START_METRIC_PREFIX = "WebMethods/SAP/SAPLISTENER/START/";
	public static final String SAPLISTENER_STOP_METRIC_PREFIX = "WebMethods/SAP/SAPLISTENER/STOP/";

	public static void addAttribute(Map<String,Object> attributes, String key, Object value) {
		if(attributes != null && key != null && !key.isEmpty() && value != null) {
			attributes.put(key,value);
		}
	}

	public static void addSAPListener(Map<String,Object> attributes, SAPListener listener) {
		if(listener != null) {
			addAttribute(attributes,"Alias", listener.getAlias());
			addAttribute(attributes,"GatewayHost", listener.getGatewayHost());
			addAttribute(attributes,"GatewayService", listener.getGatewayService());
			addAttribute(attributes,"ProgID", listener.getProgID());
			addAttribute(attributes,"RepositoryAlias", listener.getRepositoryAlias());

			SAPSystem sapSystem = listener.getSAPSystem();
			addSAPSystem(attributes, sapSystem);
		}
	}
	
	public static void addSAPSystem(Map<String,Object> attributes, SAPSystem system) {
		if(attributes != null && system != null) {
			addAttribute(attributes,"SAPSystem-Alias", system.getAlias());
			addAttribute(attributes,"SAPSystem-AppServerHost", system.getAppServerHost());
			addAttribute(attributes,"SAPSystem-Client", system.getClient());
			addAttribute(attributes,"SAPSystem-MessageServerHost", system.getMessageServerHost());
			addAttribute(attributes,"SAPSystem-SAPSystemID", system.getSAPSystemID());
			addAttribute(attributes,"SAPSystem-SNCMyName", system.getSNCMyName());
			addAttribute(attributes,"SAPSystem-SNCPartnerName", system.getSNCPartnerName());

		}
		
	}
}
