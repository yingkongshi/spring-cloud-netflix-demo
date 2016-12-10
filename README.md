# spring-cloud-netflix-demo说明

## 1.概述

1. ### demo共分为四个模块：

   | 模块名称           | 说明                                |
   | -------------- | --------------------------------- |
   | eureka-server  | 注册中心，目前的配置文件已支持Peer Awareness(双机) |
   | config-server  | 配置服务，保存各模块的配置文件，可启动多个             |
   | simple-service | 服务提供者(Provider)，可启动多个             |
   | simple-client  | 服务调用者(Consumer)，可启动多个             |

2. ### 技术栈

   - 服务的注册发现功能使用了eureka
   - Consumer调用Provider时的熔断机制使用了Hystrix
   - 目前还未加入负载均衡，Hystrix可以继承Ribbon做LB

## 2.配置与启动参数

1. ### /etc/hosts配置

   为模拟eureka-server的集群模式，需启动两个eureka-server，需要在/etc/hosts中添加域名映射（IP可根据自己的机器做修改）

   ```
   192.168.182.129 peer1
   192.168.182.129 peer2
   ```

2. ### 启动参数

   | 模块名称                 | 启动参数                           |
   | -------------------- | ------------------------------ |
   | eureka-server(peer1) | -Dspring.profiles.active=peer1 |
   | eureka-server(peer2) | -Dspring.profiles.active=peer2 |
   | config-server        | 无                              |
   | simple-service       | 无                              |
   | simple-client        | 无                              |


## 3.启动

1. ### 启动顺序

   ​    启动五个模块（包含eureka-server的双机）时，需先启动eureka-server(peer1)、eureka-server(peer2)，然后启动config-server，最后启动两个服务。

2. ### 启动时异常

   单独启动一台eureka-server或两台中的一台eureka-server挂掉时，正常的一台会报错，这是因为无法连接到另外一台机器，但可以正常对外提供注册和发现服务

   ```
   com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
   com.sun.jersey.api.client.ClientHandlerException: java.net.ConnectException: Connection refused
   ```

3. ### 状态查看

   管理页面可以查看服务的注册状态，两台eureka-server的页面基本上一样

   ```
   http://peer1:8761/
   http://peer2:8762/

   http://peer1:8761/health
   http://peer2:8762/health
   ```
