package com.springcloud.simpleclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.simpleclient.service.SimpleClientService;

@RestController
public class SimpleClientController {
	private SimpleClientService simpleClient;
	
	@RequestMapping("helloclient")
	public String getHello(String name) {
		return simpleClient.getHelloString();
	}
}
