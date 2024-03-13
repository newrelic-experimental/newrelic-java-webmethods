package com.wm.app.tn.db;

import java.sql.Connection;
import java.util.Hashtable;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.app.tn.doc.BizDocAttribute;

@SuppressWarnings("rawtypes")
@Weave
class BDAttributeOperations {

	@Trace(dispatcher = true)
	static void getAll(Hashtable cache, boolean includeDeleted)  {
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	static void getAllSystemAttributes(Hashtable systemAttributes) {
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	static boolean insert(Connection conn, BizDocAttribute a) {
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	static boolean update(Connection conn, BizDocAttribute a) {
		return Weaver.callOriginal();
	}
}
