package com.wm.app.tn.acdl.repository;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class DeploymentRepository {
	
	public void write(InputStream dataStream, InputStream descriptorSteam, String version, String bundleName) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("Version", version);
		traced.addCustomAttribute("BundleName", bundleName);
		traced.setMetricName("Custom","WebMethods","DeploymentRepository",getClass().getSimpleName(),"write");
		Weaver.callOriginal();
	}

	public void write(ByteArrayOutputStream dataStream, ByteArrayOutputStream descriptorSteam, String version, String bundleName) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.addCustomAttribute("Version", version);
		traced.addCustomAttribute("BundleName", bundleName);
		traced.setMetricName("Custom","WebMethods","DeploymentRepository",getClass().getSimpleName(),"write");
		Weaver.callOriginal();
	}


}
