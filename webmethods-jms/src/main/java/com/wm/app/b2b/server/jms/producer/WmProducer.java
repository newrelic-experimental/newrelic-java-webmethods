package com.wm.app.b2b.server.jms.producer;

import java.io.InputStream;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Topic;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class WmProducer extends Producer {

	@Trace
	Message send(InputStream inputStream, Destination destination) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		String destName = "Unknown";
		if(destination != null) {
			if(destination instanceof Queue) {
				try {
					destName = ((Queue)destination).getQueueName();
				} catch (JMSException e) {
				}
			} else if(destination instanceof Topic) {
				try {
					destName = ((Topic)destination).getTopicName();
				} catch (JMSException e) {
				}
			}
			traced.setMetricName("Custom","WebMethods","Producer","send",destName);
		} else {
			if(_sender != null) {
				try {
					Destination dest = _sender.getDestination();
					if(dest instanceof Queue) {
							destName = ((Queue)dest).getQueueName();
					} else if(dest instanceof Topic) {
							destName = ((Topic)dest).getTopicName();
					}
				} catch (JMSException e) {
				}
				
				traced.setMetricName("Custom","WebMethods","Producer","send",destName);
				
			} else {
				traced.setMetricName("Custom","WebMethods","Producer","send");
			}
		}
		return Weaver.callOriginal();
	}
}


