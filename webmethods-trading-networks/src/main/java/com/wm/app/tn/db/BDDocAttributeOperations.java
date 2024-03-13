package com.wm.app.tn.db;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.app.tn.doc.BizDocEnvelope;
import com.wm.data.IData;

@Weave
public abstract class BDDocAttributeOperations {

	@Trace
	public static int addAttributes(BizDocEnvelope doc, String[] attributeNames, boolean overwrite) {
		return Weaver.callOriginal();
	}
	
	@Trace
	static void getAttributeValues(Connection conn, BizDocEnvelope doc, String archive)  {
		Weaver.callOriginal();
	}
	
	@Trace
	public static void getAttributeValues(Connection conn, String[] doc_ids, HashMap<String, BizDocEnvelope> bizdocsMap) {
		Weaver.callOriginal();
	}

	@Trace
	public static List<IData> getDocQueryResults(String code, List<Object> parameters) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Code", code);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static List<Object> getDocumentQueryResults(String code, List<Object> parameters) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Code", code);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static void removeAttribute(Connection conn, String docId, String a_id) {
		Weaver.callOriginal();
	}
	
	@Trace
	public static int setAttributeValues(Connection conn, BizDocEnvelope doc) {
		return Weaver.callOriginal();
	}
	
	@Trace
	public static int updateAttributeValues(BizDocEnvelope doc) {
		return Weaver.callOriginal();
	}
	
	@Trace
	public static boolean updateSystemAttributes(BizDocEnvelope doc)  {
		return Weaver.callOriginal();
	}
}
