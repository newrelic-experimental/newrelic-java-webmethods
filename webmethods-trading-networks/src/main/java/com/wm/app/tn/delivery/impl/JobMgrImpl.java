package com.wm.app.tn.delivery.impl;

import java.util.List;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.wm.app.tn.delivery.DeliveryException;
import com.wm.app.tn.delivery.GuaranteedJob;
import com.wm.app.tn.delivery.JobMgr;

@Weave(type = MatchType.BaseClass)
abstract class JobMgrImpl extends JobMgr {

	public void changeServerForJob(String var1, String var2) throws DeliveryException {

	}

	public GuaranteedJob getJob(String var1, boolean var2) {
		GuaranteedJob resultJob = Weaver.callOriginal();
		if(resultJob.token != null) {
			resultJob.token.link();
		} else {
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				resultJob.token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}
		return resultJob;
	}

	public GuaranteedJob getAnyJob(String var1, boolean var2) {
		GuaranteedJob resultJob = Weaver.callOriginal();
		if(resultJob.token != null) {
			resultJob.token.link();
		} else {
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				resultJob.token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}
		return resultJob;
	}

	public GuaranteedJob getJob(String var1, boolean var2, boolean var3) {
		GuaranteedJob resultJob = Weaver.callOriginal();
		if(resultJob.token != null) {
			resultJob.token.link();
		} else {
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				resultJob.token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}
		return resultJob;
	}

	public GuaranteedJob getAnyJob(String var1, boolean var2, boolean var3) {
		GuaranteedJob resultJob = Weaver.callOriginal();
		if(resultJob.token != null) {
			resultJob.token.link();
		} else {
			Token t = NewRelic.getAgent().getTransaction().getToken();
			if(t != null && t.isActive()) {
				resultJob.token = t;
			} else if(t != null) {
				t.expire();
				t = null;
			}
		}
		return resultJob;
	}

	@Trace
	public void removeJob(String var1) throws DeliveryException {
		Weaver.callOriginal();
	}
	
	@SuppressWarnings("unused")
	private void deleteJob(GuaranteedJob job) {
		if(job.token != null) {
			job.token.expire();
			job.token = null;
		}
		Weaver.callOriginal();
	}

	@Trace
	public void restartJob(String var1) throws DeliveryException {
		Weaver.callOriginal();
	}
	
	@SuppressWarnings("unused")
	private void restartJob(GuaranteedJob job) {
		if(job.token != null) {
			job.token.link();
		}
		Weaver.callOriginal();
	}

	@Trace(async = true)
	public void startJob(GuaranteedJob job, boolean hold) throws DeliveryException {
		if(job.token != null) {
			job.token.link();
		}
		Weaver.callOriginal();
	}

	@SuppressWarnings("unused")
	private void stopJob(GuaranteedJob job) {
		if(job.token != null) {
			job.token.expire();
			job.token = null;
		}
		Weaver.callOriginal();
	}

	@Trace
	public void processJobsAsync(List<String> var1, String var2, Action var3, boolean var4) {
		Weaver.callOriginal();
	}

	@Weave(type = MatchType.BaseClass)
	protected static class JobExecutor {
		
		GuaranteedJob job = Weaver.callOriginal();
		
		@Trace(async = true)
		public void run() {
			if(job.token != null) {
				job.token.link();
			}
			Weaver.callOriginal();
		}
	}

}
