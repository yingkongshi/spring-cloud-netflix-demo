package com.springcloud.simpleservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleServerController {

  @Value("${simple-service.name}")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @RequestMapping("/hello")
  public String hello() {
    return "hello " + name;
  }
}
