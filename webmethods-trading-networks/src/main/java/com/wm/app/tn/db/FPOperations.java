package com.wm.app.tn.db;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Vector;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;
import com.wm.app.tn.err.ActivityLogEntry;
import com.wm.data.IData;

@Weave
public abstract class FPOperations {
	
	@Trace
	public static IData[] queryRolePermissions(String[] role, String[] permission) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportStringArray(attributes, "Permission", permission);
		TNUtils.reportStringArray(attributes, "Role", role);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static IData[] queryRolePermissionsByPermName(String permission) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Permission", permission);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static void modify(IData modify, String activityClassName) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ActivityClassName", activityClassName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}

	@Trace
	public static int addFPs(Connection conn, IData add, Vector<String> errors, Vector<ActivityLogEntry> logs, String activityClassName) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ActivityClassName", activityClassName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static String removeFPs(Connection conn, IData remove, Vector<String> errors, Vector<ActivityLogEntry> logs, String activityClassName) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ActivityClassName", activityClassName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static String[] listFunctionalPrivileges() {
		return Weaver.callOriginal();
	}

	@Trace
	public static String[] listFunctionalPrivileges(String roleName) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "RoleName", roleName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean checkRoleAssociation(Connection conn, String roleName) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "RoleName", roleName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

}
