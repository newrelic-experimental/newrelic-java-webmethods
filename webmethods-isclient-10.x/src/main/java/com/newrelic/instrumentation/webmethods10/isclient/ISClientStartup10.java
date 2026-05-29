package com.newrelic.instrumentation.webmethods10.isclient;

import java.lang.instrument.Instrumentation;

import com.newrelic.agent.TracerService;
import com.newrelic.agent.core.CoreService;
import com.newrelic.agent.instrumentation.ClassTransformerService;
import com.newrelic.agent.service.ServiceFactory;

public class ISClientStartup10 {
	
	public static void premain(String args, Instrumentation inst) {
		
	}


	public void initialize() {
		
		boolean b = setUp();
		if(!b)  {
			
		}
		
	}
	
	public static boolean setUp() {
		TracerService tracerService = ServiceFactory.getTracerService();
		ClassTransformerService classTransformationService = ServiceFactory.getClassTransformerService();
		CoreService coreService = ServiceFactory.getCoreService();
		
		if(tracerService != null && classTransformationService != null && coreService != null) {
			//WM10FlowClassTransformer flowTransformer = new WM10FlowClassTransformer();
			
		}
		
		return false;
	}
	
	private static class Checker implements Runnable {
		
		public void run() {
			
			boolean b = setUp();
			
		}
	}
}
