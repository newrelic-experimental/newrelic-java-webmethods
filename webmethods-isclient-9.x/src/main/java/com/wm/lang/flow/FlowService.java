package com.wm.lang.flow;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.webmethods.isclient.WebMethodsUtils;
import com.wm.data.IData;
import com.wm.lang.ns.NSName;
import com.wm.lang.ns.NSPackage;
import com.wm.lang.ns.NSService;
import com.wm.lang.ns.NSServiceType;
import com.wm.lang.ns.Namespace;
import com.wm.util.Values;

@Weave(type = MatchType.BaseClass)
public abstract class FlowService extends NSService {

	public static final NSServiceType SVC_FLOW = Weaver.callOriginal();
	
	FlowRoot flow = Weaver.callOriginal();
	
	public FlowService(Namespace ns, NSPackage pkg, NSName node, FlowRoot flow) {
		super(ns, pkg, node, SVC_FLOW);
	}
	
	protected FlowService(Namespace ns, Values vals) {
		super(ns, vals);
	}

	@Override
	@Trace(dispatcher = true)
	public Values invoke(Values var1) throws Exception {
		NSName n = getNSName();
		String name = n!= null ? n.toString() : null;
		String root = flow.getName();
		if(name != null) {
			String[] metricName = WebMethodsUtils.getMetricName("Custom","WebMethods","FlowService",name,"invoke",root);
			NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
			String[] txnName = WebMethodsUtils.getMetricName("FlowService",name,root);
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_LOW, false, "FlowService", txnName);
		}
		return Weaver.callOriginal();
	}

	@Override
	@Trace(dispatcher = true)
	public IData invoke(IData var1) throws Exception {
		NSName n = getNSName();
		String name = n!= null ? n.toString() : null;
		String root = flow.getName();
		if(name != null) {
			String[] metricName = WebMethodsUtils.getMetricName("Custom","WebMethods","FlowService",name,"invoke",root);
			NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
			String[] txnName = WebMethodsUtils.getMetricName("FlowService",name,root);
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_LOW, false, "FlowService", txnName);
		}
		return Weaver.callOriginal();
	}

	@Trace
	public FlowState invokeMonitor(Values var1) {
		NSName n = getNSName();
		String name = n!= null ? n.toString() : null;
		String root = flow.getName();
		if(name != null) {
			String[] metricName = WebMethodsUtils.getMetricName("Custom","WebMethods","FlowService",name,"invokeMonitor",root);
			NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		}
		return Weaver.callOriginal();
	}

	@Trace
	public FlowState invokeMonitor(IData var1) {
		NSName n = getNSName();
		String name = n!= null ? n.toString() : null;
		String root = flow.getName();
		if(name != null) {
			String[] metricName = WebMethodsUtils.getMetricName("Custom","WebMethods","FlowService",name,"invokeMonitor",root);
			NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		}
		return Weaver.callOriginal();
	}

	@Trace
	public FlowState invokeMonitorInto(Values var1, FlowState var2) {
		NSName n = getNSName();
		String name = n!= null ? n.toString() : null;
		String root = flow.getName();
		if(name != null) {
			String[] metricName = WebMethodsUtils.getMetricName("Custom","WebMethods","FlowService",name,"invokeMonitorInto",root);
			NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		}
		return Weaver.callOriginal();
	}

	@Trace
	public FlowState invokeMonitorInto(IData var1, FlowState var2) {
		NSName n = getNSName();
		String name = n!= null ? n.toString() : null;
		String root = flow.getName();
		if(name != null) {
			String[] metricName = WebMethodsUtils.getMetricName("Custom","WebMethods","FlowService",name,"invokeMonitorInto",root);
			NewRelic.getAgent().getTracedMethod().setMetricName(metricName);
		}
		return Weaver.callOriginal();
	}

}
