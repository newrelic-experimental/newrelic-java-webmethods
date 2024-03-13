package com.wm.app.tn.db;

import java.sql.Connection;
import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;
import com.wm.app.tn.err.ActivityLogEntry;
import com.wm.app.tn.onboarding.Invite;
import com.wm.data.IData;

@Weave
public abstract class OnboardingOps {
	
	@Trace
	public static String addInvite(Invite invite) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportInvite(attributes, invite);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static String getSubStatus(String partnerID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerId", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static String updateInvitationSent(Invite invite) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportInvite(attributes, invite);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static Invite getInvite(String inviteId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "InviteId", inviteId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static Invite getInviteByPartner(String partnerID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerId", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static void updateInvite(Invite invite) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportInvite(attributes, invite);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace
	public static boolean deleteInvite(String inviteId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "InviteId", inviteId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static void updateInviteStatus(Connection conn, String inviteId, String status) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "InviteId", inviteId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace
	public static IData[] getTemplates() {
		return Weaver.callOriginal();
	}

	@Trace
	public static IData query(InviteQuery query, int pageSize, int maxR, int qryT, int threshold, String queryId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "QueryId", queryId);
		TNUtils.reportInviteQuery(attributes, query);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static IData[] queryTemplates(String templateName) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "TemplateName", templateName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static void saveQuestionnaireData(String partnerID, String templateID, String questionnaireBytes, String status) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerId", partnerID);
		TNUtils.reportAttribute(attributes, "TemplateId", templateID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace
	public static String getQuestionnaireData(String partnerID, String templateID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerId", partnerID);
		TNUtils.reportAttribute(attributes, "TemplateId", templateID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static ActivityLogEntry createActivityLog(String partnerID, String questionnaire) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerId", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static void insertActivityLog(String partnerID, String questionnaire) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerId", partnerID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace
	public static void updateQuestionnaireStatus(String partnerID, String templateID, String approvalStatus) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "PartnerId", partnerID);
		TNUtils.reportAttribute(attributes, "TemplateId", templateID);
		TNUtils.reportAttribute(attributes, "ApprovalStatus", approvalStatus);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

}
