package com.wm.app.tn.db;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;
import com.wm.app.tn.doc.BizDocContentPart;
import com.wm.app.tn.doc.BizDocEnvelope;
import com.wm.data.IData;
import com.wm.util.tspace.Reservation;

@SuppressWarnings("rawtypes")
@Weave
public class BizDocStore {

	@Trace
	public static boolean acceptDocument(String rec_id, String doc_id, boolean err) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", doc_id);
		TNUtils.reportAttribute(attributes, "RecId", rec_id);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static int archiveDocuments(Timestamp thresh) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Threshold", thresh.toString());
		return Weaver.callOriginal();
	}

	@Trace
	public static int deleteDocuments(Timestamp thresh) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("Threshold", thresh.toString());
		return Weaver.callOriginal();
	}

	@Trace
	public static int deleteDocuments(String docId, String sysStatus, String userStatus, String docTypeId, boolean delRelated) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportAttribute(attributes, "SystemStatus", sysStatus);
		TNUtils.reportAttribute(attributes, "UserStatus", userStatus);
		TNUtils.reportAttribute(attributes, "DocTypeId", docTypeId);
		TNUtils.reportAttribute(attributes, "DeleteRelated", delRelated);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean queueForPolling(BizDocEnvelope doc) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportBizDocEnvelope(attributes, doc);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean queueForDelivery(BizDocEnvelope doc) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportBizDocEnvelope(attributes, doc);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static String[] pollDocuments(String rec_id) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "RecId", rec_id);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static String[] pollDocumentsForUser(String username) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Username", username);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static IData queryEvents(EventQuery query, int pageSize, int maxRowCount, int queryTimeout, int threshold, String queryId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "QueryId", queryId);
		TNUtils.reportEventQuery(attributes, query);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static int deleteEvents(EventQuery query) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportEventQuery(attributes, query);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static IData queryDocuments(SimpleDocQuery query, boolean agg, int pageSize, int maxRowCount, int queryTimeout, int threshold, String queryId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "QueryId", queryId);
		TNUtils.reportAttribute(attributes, "Threshold", threshold);
		TNUtils.reportSimpleDocQuery(attributes, query);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static BizDocEnvelope getDocument(String docId, boolean content, String[] includeParts,String[] excludeParts) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportAttribute(attributes, "Content", content);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static BizDocEnvelope[] getDocuments(String[] docIds, boolean contents, boolean attribs, boolean errors) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportStringArray(attributes, "DocIds", docIds);
		TNUtils.reportAttribute(attributes, "Contents", contents);
		TNUtils.reportAttribute(attributes, "Attributes", attribs);
		TNUtils.reportAttribute(attributes, "Errors", errors);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static BizDocEnvelope getDocument(String docId, boolean content, String[] includeParts, String[] excludeParts, String archive) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportAttribute(attributes, "Content", content);
		TNUtils.reportAttribute(attributes, "Archive", archive);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static BizDocEnvelope getDocument(Connection conn, String docId, boolean content, String[] includeParts, String[] excludeParts, boolean includeBizDocErrors) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportAttribute(attributes, "Content", content);
		TNUtils.reportAttribute(attributes, "InculudeBizDocErrors", includeBizDocErrors);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static BizDocEnvelope getDocument(Connection conn, String docId, boolean content, String[] includeParts, String[] excludeParts, String archive, boolean includeBizDocErrors) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportAttribute(attributes, "Content", content);
		TNUtils.reportAttribute(attributes, "InculudeBizDocErrors", includeBizDocErrors);
		TNUtils.reportAttribute(attributes, "Archive", archive);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static int checkUnique(int which, String internalId, String natId, String senderId, String otherId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "InternalId", internalId);
		TNUtils.reportAttribute(attributes, "NatId", natId);
		TNUtils.reportAttribute(attributes, "SenderId", senderId);
		TNUtils.reportAttribute(attributes, "OtherId", otherId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean documentExists(String docId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean documentExists(String docId, boolean isArchive) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportAttribute(attributes, "IsArchive", isArchive);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static String[] getUserStatus() {
		return Weaver.callOriginal();
	}

	@Trace
	public static IData getComments(String docId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean insertDocument(BizDocEnvelope doc, boolean content) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Content", content);
		TNUtils.reportBizDocEnvelope(attributes, doc);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean insertContentPart(String docId, BizDocContentPart part) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportBizDocContentPart(attributes, part);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean replaceContentPart(String docId, BizDocContentPart part) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportBizDocContentPart(attributes, part);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static BizDocContentPart retrieveContentPart(String docId, String partName) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportAttribute(attributes, "PartName", partName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static BizDocContentPart retrieveContentPart(String docId, String partName, int partIndex) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportAttribute(attributes, "PartName", partName);
		TNUtils.reportAttribute(attributes, "PartIndex", partIndex);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static int addAttributes(BizDocEnvelope doc, String[] attributeNames, boolean overwrite) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Overwrite", overwrite);
		TNUtils.reportBizDocEnvelope(attributes, doc);
		TNUtils.reportStringArray(attributes, "Attributes", attributeNames);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static int updateAttributes(BizDocEnvelope doc) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportBizDocEnvelope(attributes, doc);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static void updateSystemAttributes(BizDocEnvelope doc) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportBizDocEnvelope(attributes, doc);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace
	public static boolean updateConversationID(BizDocEnvelope doc) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportBizDocEnvelope(attributes, doc);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean updateComments(String doc_id, String comment) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocID", doc_id);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean changeStatus(BizDocEnvelope doc, String sys, String usr, String dataType) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportBizDocEnvelope(attributes, doc);
		TNUtils.reportAttribute(attributes, "System", sys);
		TNUtils.reportAttribute(attributes, "User", usr);
		TNUtils.reportAttribute(attributes, "DataType", dataType);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean changeRepeatByOne(BizDocEnvelope doc) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportBizDocEnvelope(attributes, doc);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean changeRepeatByOneForResubmission(BizDocEnvelope doc) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportBizDocEnvelope(attributes, doc);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean changeStatus(String doc_id, String sys, String usr) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", doc_id);
		TNUtils.reportAttribute(attributes, "System", sys);
		TNUtils.reportAttribute(attributes, "User", usr);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean changeStatus(String doc_id, String sys, String usr, String dbSource) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", doc_id);
		TNUtils.reportAttribute(attributes, "System", sys);
		TNUtils.reportAttribute(attributes, "User", usr);
		TNUtils.reportAttribute(attributes, "DBSource", dbSource);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean changeRepeatByOne(String doc_id, int repeatNum) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", doc_id);
		TNUtils.reportAttribute(attributes, "RepeatNumber", repeatNum);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean changeRepeatByOneForResubmission(String doc_id, int repeatNum) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", doc_id);
		TNUtils.reportAttribute(attributes, "RepeatNumber", repeatNum);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static Vector getDocInfo(String documentId, String senderId, String receiverId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocumentId", documentId);
		TNUtils.reportAttribute(attributes, "SenderId", senderId);
		TNUtils.reportAttribute(attributes, "ReceiverId", receiverId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean relateDocuments(BizDocEnvelope from, BizDocEnvelope to, String rel) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportBizDocEnvelope(attributes,"From", from);
		TNUtils.reportBizDocEnvelope(attributes,"To", to);
		TNUtils.reportAttribute(attributes, "Related", rel);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean relateDocuments(String from, String to, String rel) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "From", from);
		TNUtils.reportAttribute(attributes, "To", to);
		TNUtils.reportAttribute(attributes, "Related", rel);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static IData getRelatedDocuments(String docId, String groupId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "DocId", docId);
		TNUtils.reportAttribute(attributes, "GroupId", groupId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static ArrayList executeQuery(String query, Timestamp min_ts, Timestamp max_ts) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Query", query);
		TNUtils.reportAttribute(attributes, "Min_Timestamp", min_ts);
		TNUtils.reportAttribute(attributes, "Max_Timestamp", max_ts);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static ArrayList<String> updateTimestamp(String[] ids, String[] crt_time, String[] last_mod, String query) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Query", query);
		TNUtils.reportStringArray(attributes, "Ids", ids);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static ArrayList<String> updateTimestamp(String[] ids, String[] last_mod, String query) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Query", query);
		TNUtils.reportStringArray(attributes, "Ids", ids);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static Reservation retrieveContentPartFromDB(String internalId, String partName) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "InternalId", internalId);
		TNUtils.reportAttribute(attributes, "PartName", partName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

}
