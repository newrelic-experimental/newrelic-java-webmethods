package com.wm.app.tn.db;

import java.util.Enumeration;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.app.tn.doc.BizDocAttribute;

@SuppressWarnings("rawtypes")
@Weave
public abstract class BizDocAttributeStore {

	@Trace
	public static void fillCache(boolean includeDeleted)  {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("IncludeDeleted", includeDeleted);
		Weaver.callOriginal();
	}
	
	@Trace
	public static BizDocAttribute get(String id, boolean returnDeleted) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("ID", id);
		traced.addCustomAttribute("ReturnDeleted", returnDeleted);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static BizDocAttribute getByName(String name, boolean returnDeleted) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("Name", name);
		traced.addCustomAttribute("ReturnDeleted", returnDeleted);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static boolean insert(BizDocAttribute a) {
		return Weaver.callOriginal();
	}
	
	@Trace
	public static Enumeration list(boolean includeDeleted) {
		return Weaver.callOriginal();
	}
	
	@Trace
	public static Enumeration listSystemAttributes() {
		return Weaver.callOriginal();
	}
	
	@Trace
	public static String[] listTypesForAttribute(String a_id) {
		return Weaver.callOriginal();
	}
	
	@Trace
	public static String[] queryAttributes(String regexName) {
		NewRelic.getAgent().getTracedMethod().addCustomAttribute("RegExName", regexName);
		return Weaver.callOriginal();
	}
	
	@Trace
	public static boolean setPersist(String id, boolean persist) {
		return Weaver.callOriginal();
	}
	
	@Trace
	public static boolean update(BizDocAttribute a) {
		return Weaver.callOriginal();
	}
}
