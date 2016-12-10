package com.springcloud.simpleclient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;

@Service
public class SimpleClientService {
  @Autowired
  private DiscoveryEnabledNIWSServerList discoveryClient;
  @Autowired
  private RestTemplate restTemplate;

  final String SERVICE_NAME = "simple-service";

  @HystrixCommand(fallbackMethod = "fallbackSearchAll")
  public String getHelloString() {
    List<DiscoveryEnabledServer> instance = discoveryClient.getUpdatedListOfServers();
    String url = "http://" + instance.get(0).getHostPort() + "/hello";
    String result = restTemplate.getForObject(url, String.class);
    System.out.println(result);
    return result;
  }


  private String fallbackSearchAll() {
    return "hello mock";
  }

}
