package com.wm.app.tn.db;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Hashtable;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.labs.webmethods.tn.TNUtils;

@SuppressWarnings("rawtypes")
@Weave
public abstract class Lookup {

	@Trace
	public static Hashtable getContactTypes() {
		return Weaver.callOriginal();
	}

	@Trace
	public static Hashtable getIDTypes() {
		return Weaver.callOriginal();
	}

	@Trace
	public static String getIDTypeDescription(int idType) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "IDType", idType);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static byte[] getStoredQueries(int type, String profileID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Type", type);
		TNUtils.reportAttribute(attributes, "ProfileId", profileID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static Hashtable getIDTypeDescriptionHash() {
		return Weaver.callOriginal();
	}

	@Trace
	public static Hashtable getBinaryTypes() {
		return Weaver.callOriginal();
	}

	@Trace
	public static Hashtable getBinaryTypes(Connection conn) {
		return Weaver.callOriginal();
	}

	@Trace
	public static int addLookupType(int lookupTbl, Integer type, String desc, Byte label) {
		return Weaver.callOriginal();
	}

	@Trace
	public static Integer getNextTypeNumberForCloudMode(int type) {
		return Weaver.callOriginal();
	}

	@Trace
	public static void changeLookupType(int lookupTbl, Integer type, String desc) {
		Weaver.callOriginal();
	}

	@Trace
	public static void removeLookupType(int lookupTbl, Integer type) {
		Weaver.callOriginal();
	}

	@Trace
	public static Hashtable getGroups() {
		return Weaver.callOriginal();
	}

	@Trace
	public static Hashtable getGroupsByName(String name) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "Name", name);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static short addGroup(String desc) {
		return Weaver.callOriginal();
	}

	@Trace
	public static short addGroup(short id, String desc) {
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean changeGroup(short id, String desc) {
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean deleteGroup(short id) {
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean isFieldsExist(short groupId) {
		return Weaver.callOriginal();
	}

	@Trace
	public static Hashtable getLookupHashByName(int lookupTbl, String name) {
		return Weaver.callOriginal();
	}

	@Trace
	public static short nextFieldGroup() {
		return Weaver.callOriginal();
	}

	@Trace
	public static Hashtable getAllProfileGroups() {
		return Weaver.callOriginal();
	}

	@Trace
	public static HashMap getProfileGroup(String groupID) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "GroupId", groupID);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static Hashtable queryProfileGroupbyName(String profileGroupName) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ProfileGroupName", profileGroupName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static String addProfileGroup(String profileGroupId, String profileGroupName) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ProfileGroupName", profileGroupName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static String addProfileGroup(String profileGroupId, String profileGroupName, String description) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ProfileGroupName", profileGroupName);
		TNUtils.reportAttribute(attributes, "ProfileGroupId", profileGroupId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static int changeProfileGroup(String profileGroupId, String profileGroupName) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ProfileGroupName", profileGroupName);
		TNUtils.reportAttribute(attributes, "ProfileGroupId", profileGroupId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static int changeProfileGroupDescription(String profileGroupId, String description) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ProfileGroupId", profileGroupId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static int deleteProfileGroup(String profileGroupId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ProfileGroupId", profileGroupId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}

	@Trace
	public static boolean isProfileGroupIdInUse(String profileGroupId) {
		HashMap<String, Object> attributes = new HashMap<>();
		TNUtils.reportAttribute(attributes, "ProfileGroupId", profileGroupId);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
}
