package com.wm.app.b2b.server;

import java.lang.reflect.Method;
import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.b2bserver.WebMethodsUtils;
import com.wm.data.IData;
import com.wm.lang.ns.NSName;
import com.wm.lang.ns.NSServiceType;
import com.wm.util.Values;

@Weave(type = MatchType.BaseClass)
public abstract class JavaService extends BaseService {
	
	Method method = Weaver.callOriginal();

	public JavaService(Package pkg, NSName name, Values vals) {
		super(pkg, name, NSServiceType.create("java", "default"));
	}

	public abstract String getClassName();

	public abstract String getMethodName();
	

	@Trace(dispatcher = true)
	public IData baseInvoke(IData var1) throws Exception {
		NSName n = getNSName();
		String name = n!= null ? n.toString() : null;
		String[] metricName = WebMethodsUtils.getMetricName("Custom","WebMethods","JavaService","baseInvoke",name);
		NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		if(name != null) {
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_LOW, false, "JavaService", "JavaService",name);
		} else {
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_LOW, false, "JavaService", "JavaService","UnknownName");
		}
		HashMap<String, Object> attributes = new HashMap<>();
		String className = getClassName();
		if(className == null || className.isEmpty()) {
			if(method != null) {
				className = method.getDeclaringClass().getName();
			} else {
				className = null;
			}
		}
		
		WebMethodsUtils.addValue(attributes, "ClassName",className);
		String methodName = getMethodName();
		if(methodName == null || methodName.isEmpty()) {
			if(method != null) {
				methodName = method.getName();
			} else {
				methodName = null;
			}
		}
		WebMethodsUtils.addValue(attributes, "MethodName", methodName);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		try {
			return Weaver.callOriginal();
		} catch(Exception e) {
			NewRelic.noticeError(e);
			throw e;
		}
	}



}
