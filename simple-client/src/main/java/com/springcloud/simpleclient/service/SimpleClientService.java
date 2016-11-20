package com.springcloud.simpleclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class SimpleClientService {
	@Autowired
	private EurekaClient discoveryClient;
	@Autowired
	private RestTemplate restTemplate;

	final String SERVICE_NAME="simple-service";

	@HystrixCommand(fallbackMethod = "fallbackSearchAll")
	public String getHelloString() {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("simple-service", false);
		String url = "http://"+instance.getIPAddr() + ":" + instance.getPort()+"/hello";
		String result = restTemplate.getForObject(url, String.class);
		System.out.println(result);
		return result;
	}


	private String fallbackSearchAll() {
		return "hello mock";
	}

}
