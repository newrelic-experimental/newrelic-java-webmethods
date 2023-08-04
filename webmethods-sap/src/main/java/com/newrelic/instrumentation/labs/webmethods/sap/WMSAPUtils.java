package com.newrelic.instrumentation.labs.webmethods.sap;

import java.util.Map;

import com.sap.conn.idoc.IDocDocument;
import com.sap.conn.idoc.IDocDocumentList;
import com.wm.adapter.sap.SAPListener;
import com.wm.adapter.sap.SAPSystem;

public class WMSAPUtils {
	
	public static final String SAPLISTENER_START_METRIC_PREFIX = "WebMethods/SAP/SAPLISTENER/START/";
	public static void addAttribute(Map<String,Object> attributes, String key, Object value) {
		if(attributes != null && key != null && !key.isEmpty() && value != null) {
			attributes.put(key,value);
		}
	}

	public static void addIDocDocumentList(Map<String,Object> attributes, IDocDocumentList iDocList) {
		addAttribute(attributes, "IDocDocumentList-DocType", iDocList.getIDocType());	
		addAttribute(attributes, "IDocDocumentList-NumberOfDocs", iDocList.getNumDocuments());	
		addAttribute(attributes, "IDocDocumentList-DocTypeExtension", iDocList.getIDocTypeExtension());	
	}
	
	public static void addIDocDocument(Map<String,Object> attributes, IDocDocument doc) {
		addAttribute(attributes, "IDocDocument-Client", doc.getClient());
		addAttribute(attributes, "IDocDocument-Direction", doc.getDirection());
		addAttribute(attributes, "IDocDocument-EDIMessage", doc.getEDIMessage());
		addAttribute(attributes, "IDocDocument-EDIMessageType", doc.getEDIMessageType());
		addAttribute(attributes, "IDocDocument-IDocNumber", doc.getIDocNumber());
		addAttribute(attributes, "IDocDocument-IDocType", doc.getIDocType());
		addAttribute(attributes, "IDocDocument-IDocTypeExtension", doc.getIDocTypeExtension());
		addAttribute(attributes, "IDocDocument-MessageCode", doc.getMessageCode());
		addAttribute(attributes, "IDocDocument-MessageFunction", doc.getMessageFunction());
		addAttribute(attributes, "IDocDocument-MessageType", doc.getMessageType());
		addAttribute(attributes, "IDocDocument-RecipientAddress", doc.getRecipientAddress());
		addAttribute(attributes, "IDocDocument-OutputMode", doc.getOutputMode());
		addAttribute(attributes, "IDocDocument-SenderAddress", doc.getSenderAddress());
		addAttribute(attributes, "IDocDocument-Status", doc.getStatus());
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
