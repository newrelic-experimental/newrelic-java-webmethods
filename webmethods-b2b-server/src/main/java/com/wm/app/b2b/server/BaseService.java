package com.wm.app.b2b.server;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.app.b2b.server.ns.Namespace;
import com.wm.data.IData;
import com.wm.lang.ns.NSName;
import com.wm.lang.ns.NSPackage;
import com.wm.lang.ns.NSService;
import com.wm.lang.ns.NSServiceType;
import com.wm.util.Values;

@Weave(type = MatchType.BaseClass)
public abstract class BaseService extends NSService {
	
	protected BaseService(com.wm.app.b2b.server.Package pkg, final NSName name, final NSServiceType svcType) {
		super((com.wm.lang.ns.Namespace) Namespace.current(), (NSPackage) pkg, name, svcType);
	}
	protected BaseService(NSName name, NSServiceType svcType, String pkgName) {
		this((Package) null, (NSName) name, (NSServiceType) svcType);
	}
	
	protected BaseService(Values vals) {
		super(Namespace.current(), vals);
	}
	
	protected BaseService(Values vals, String pkgName) {
		super(Namespace.current(), vals);
	}
	
	public abstract String getBinding();
	
	public abstract String getPackageName();
	
	public abstract String getTemplate() ;
	
	public abstract String getURLInvokePath();

	@Override
	@Trace
	public Values invoke(Values var1) throws Exception {
		NSName n = getNSName();
		String name = n!= null ? n.toString() : null;
		if(name != null) {
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","BaseService",getClass().getSimpleName(),name,"invoke");
		}
		return Weaver.callOriginal();
	}

	@Override
	@Trace
	public IData invoke(IData var1) throws Exception {
		NSName n = getNSName();
		String name = n!= null ? n.toString() : null;
		if(name != null) {
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","WebMethods","BaseService",getClass().getSimpleName(),name,"invoke");
		}
		return Weaver.callOriginal();
	}


}
