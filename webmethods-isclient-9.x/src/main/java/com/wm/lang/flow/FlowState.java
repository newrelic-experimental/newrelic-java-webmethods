package com.wm.lang.flow;

import java.util.HashMap;
import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class FlowState {
	
	public abstract String currentPath();

	public void setError(Exception error) {
		String current = currentPath();
		Map<String, String> attributes = new HashMap<>();
		
		if(current != null && !current.isEmpty()) {
			attributes.put("CurrentPath", current);
		}
		
		if(attributes.isEmpty()) {
			NewRelic.noticeError(error);
		} else {
			NewRelic.noticeError(error, attributes);
		}
		Weaver.callOriginal();
	}
	
	public void setError(Throwable error) {
		String current = currentPath();
		Map<String, String> attributes = new HashMap<>();
		
		if(current != null && !current.isEmpty()) {
			attributes.put("CurrentPath", current);
		}
		
		if(attributes.isEmpty()) {
			NewRelic.noticeError(error);
		} else {
			NewRelic.noticeError(error, attributes);
		}
		Weaver.callOriginal();
	}
	
}
