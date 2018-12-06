package com.example.metric;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetricFilter implements Filter {

	@Autowired
	private MetricService metricService;

	private static String ENDPOINT = "/demo/contact";
	
	private static String NAME_MONITOR = "contact";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {
		chain.doFilter(request, response);
		String uri = ((HttpServletRequest) request).getRequestURL().toString();
		if (uri.contains(ENDPOINT)) {
			metricService.increaseCount(NAME_MONITOR, ((HttpServletRequest) request).getMethod());
		}

	}
}