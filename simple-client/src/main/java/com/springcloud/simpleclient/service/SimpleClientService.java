package com.springcloud.simpleclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class SimpleClientService {
	 @Autowired	 
	 RestTemplate restTemplate;
	 
	 final String SERVICE_NAME="cloud-simple-service";
	 
	 @HystrixCommand(fallbackMethod = "fallbackSearchAll")
	 public String getHelloString() {
	        return restTemplate.getForObject("http://"+SERVICE_NAME+"/hello", String.class);
	 }
	 
	 
	 private String fallbackSearchAll() {
		 return "hello mock";
	 }
}
