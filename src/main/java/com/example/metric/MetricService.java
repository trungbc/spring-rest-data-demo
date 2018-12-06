package com.example.metric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class MetricService {

	@Autowired
	MeterRegistry registry;

	/**
	 * Increase request number
	 * 
	 * @param metricName
	 * @param method
	 */
	public void increaseCount(String metricName, String method) {
		StringBuffer name = new StringBuffer(metricName);
		Counter counter = Counter.builder(name.append(".").append(method.toLowerCase()).toString())
				.description("Request Number").register(registry);
		counter.increment();
	}

}