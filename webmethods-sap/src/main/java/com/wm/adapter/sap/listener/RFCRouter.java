package com.wm.adapter.sap.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.webmethods.sap.WMSAPUtils;
import com.sap.conn.idoc.IDocDocument;
import com.wm.adapter.sap.idoc.IDocDocumentList;
import com.wm.data.IData;
import com.wm.data.IDataCursor;

@Weave
public abstract class RFCRouter {

	@Trace
	public static void inboundProcess(RFCListener rfcL, String rfcName, String svcName, IData pipeline, String tid, int action) {
		List<String> names = new ArrayList<>();
		names.add("Custom/WebMethods/SAPAdapter/RFCRouter");
		if(rfcName != null && !rfcName.isEmpty()) {
			names.add(rfcName);
		}
		if(svcName != null && !svcName.isEmpty()) {
			names.add(svcName);
		}
		String[] array = new String[names.size()];
		names.toArray(array);
		NewRelic.getAgent().getTracedMethod().setMetricName(array);
		IDataCursor cursor = pipeline.getCursor().getCursorClone();
		if(cursor != null) {
			IDataCursor cloned = cursor.getCursorClone();
			IDocDocumentList list = null;
			while(cloned.hasMoreData() && list == null) {
				String key = cloned.getKey();
				if(key.equals("iDocList")) {
					list = (IDocDocumentList)cloned.getValue();
				}
				cloned.next();
			}
			
			if(list != null) {
				HashMap<String, Object> attributes = new HashMap<>();
				WMSAPUtils.addIDocDocumentList(attributes, list);
				NewRelic.getAgent().getInsights().recordCustomEvent("WM_IDOCLIST", attributes);
				
				
				int n = list.getNumDocuments();
				for(int i=0;i<n;i++) {
					attributes.clear();
					IDocDocument idoc = list.get(i);
					WMSAPUtils.addIDocDocument(attributes, idoc);
					NewRelic.getAgent().getInsights().recordCustomEvent("WM_IDOC", attributes);
					
				}
				
			}
		}
		Weaver.callOriginal();
	}
}
