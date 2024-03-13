package com.wm.app.tn.db;

import java.sql.Timestamp;
import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;
import com.wm.data.IData;

@Weave
public class ArchiveOperations {
	
	@Trace
	public static void cleanWorkTable() {
		Weaver.callOriginal();
	}

	@Trace
	public static synchronized long executeArchStoredProc(String sql, int afterdays, int batch)  {
		return Weaver.callOriginal();
	}

	@Trace
	public static synchronized int process(String op, Timestamp thresh, String typeId, String senderId,
			String receiverId, String sysStatus, String userStatus, IData options, String archiveName,
			Integer archiveBackOffTime, Integer archiveMaxRows, Integer archiveBatchSize, String deletionType,
			Timestamp deletemaxtsProduction, Timestamp deletemaxtsArchival) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Operation", op);
		TNUtils.reportAttribute(attributes, "Threshold", thresh);
		TNUtils.reportAttribute(attributes, "TypeId", typeId);
		TNUtils.reportAttribute(attributes, "SenderId", senderId);
		TNUtils.reportAttribute(attributes, "ReceiverId", receiverId);
		TNUtils.reportAttribute(attributes, "SystemStatus", sysStatus);
		TNUtils.reportAttribute(attributes, "UserStatus", userStatus);
		TNUtils.reportAttribute(attributes, "ArchiveName", archiveName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static int purgeData(String tableName) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("TableName", tableName);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static int purgeData(String tableName, int batchsize, int maxrows) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("TableName", tableName);
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("BatchSize", batchsize);
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("MaxRows", maxrows);

		return Weaver.callOriginal();
	}
}
