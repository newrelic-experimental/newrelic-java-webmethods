package com.wm.app.tn.db;

import java.sql.Connection;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.data.IData;

@Weave
public abstract class EventOperations {

	@Trace(dispatcher = true)
	public static int delete(EventQuery query, Connection connection) {
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public static IData query(EventQuery query, int pageSize, int maxR, int qryT, int threshold, String queryId) {
		return Weaver.callOriginal();
	}
}
