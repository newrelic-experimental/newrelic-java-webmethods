package com.wm.app.tn.db;

import java.util.Hashtable;
import java.util.Vector;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.app.tn.doc.BizDocType;
import com.wm.app.tn.profile.Profile;
import com.wm.app.tn.profile.DLSInfo;
import com.wm.app.tn.route.RoutingRuleList;
import com.wm.data.IData;

@SuppressWarnings("rawtypes")
@Weave
public abstract class Import {

	@Trace
	public static IData installData(IData data, boolean dbop, boolean force, String ruleImportOptions, boolean print) {
		return Weaver.callOriginal();
	}

	@Trace
	public static IData installData(IData data, boolean dbop, boolean force, boolean rrappend, boolean print) {
		return Weaver.callOriginal();
	}

	@Trace
	public static void installVersion(IData data, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installDLS(Vector<DLSInfo> data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installDLS(IData[] data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installFP(IData[] data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installAttributes(IData data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installTypes(IData data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installRules(RoutingRuleList data, boolean dbop, String ruleImportOptions, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installFieldDefs(Vector data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installProfiles(Vector data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void registerDeliveryServicesInfo(Profile profile) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installExtflds(Vector data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installIdTypes(IData data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installContactTypes(IData data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installBinaryTypes(IData data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installFieldGroups(IData data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installProfileGroups(IData data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installQueues(IData data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installArchSvcs(Vector data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installTPAs(Vector data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installSecurities(IData data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void installImportedQueries(byte[] data, boolean dbop, boolean force, boolean print) {
		Weaver.callOriginal();
	}

	@Trace
	public static void validateTypes(BizDocType docType, Hashtable idTypes, boolean sim) {
		Weaver.callOriginal();
	}
}
