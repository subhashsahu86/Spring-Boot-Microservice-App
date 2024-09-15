===================================================
Project Development with Microservice Architecture
===================================================

Monolithic Architecture
=======================
-> Developing all the functionlities in a single Application.

Problems With Monolithic Architecture:
=====================================
1.Tight Coupling.
2.Maintenance is Difficult.
3.Re-Deploy entire application.
4.Technology dependent.
5.Burden on server(server crash problem)
6.Single point of failure.

-> To overcome these problems, we are using Microservices Architecture.

==============
Microservices 
==============
-> It is not a technology/language.
-> It is not a framework.
-> It is not an API.
-> It is an architecture Design pattern.
-> It is universal pattern, anyone can use this to design the application.

Advantages of Microservices : 
===========================

1) Loosely Coupled.
2) Burden reduce on server.
3) Easy maintenance.
4) No single point of failure.
5) Technology Independent.
6) Quick Deliveries.

Challenges with Microservices :
==============================
1) Bounded Concept : It means identifying how many Microservices we need to develop for one 
   ---------------- application and deciding which functionlity we need to add in which microservice.

Eg : like we want to develop an application have 150+ features so let's suppose we develop 50 
     microservices so which feature is added into which microservices is a difficult task.

2) Repeated Configuration : In several Microservices we need to write  same configuration 
   ----------------------   like data source, smtp, kafka configurations etc...

3) Visiblity Problem : In Microservies Architecture we may not get chance to work with all the   
---------------------  microservices.

=========================
Microservice Architecture
=========================

-> There is no fixed architecture for microservice development.
-> we can coustmize microservice architecture according to our project.
-> As part of microservice architecture we are going to use these below components.
note: all the below components are not mendatory , we can use on demand

1) Service Registory (Eureka Server)
2) Admin server
3) Zipkin server
4) Config server
5) kafka server
6) Redis server
7) FeignClient
8) API Gateway

Service Registory : 
-------------------
-> It is used to maintain all API information like name, status, url and health at one place.
-> It is also called as service Discovery.
-> we can use "Eureka Server" as service discovery.
   Note : Eureka server is provided by spring cloud library and it is free and open-source.
   Others : Amazon service Registory, Azure Service Registory.(Paid)
-> it provides user interface to get all the API info.

Admin Server :
--------------
-> It is used to moniter and manage all the APIs at one place.
-> It provides user interface to access all APIs actuators endpoints at one place.
Ex: 
1) Health checks
2) Congig properties
3) Url Mapping
4) Beans loaded
5) Change Log levels
6) We can download Thread dumps
7) We can download Heap dumps

Zipkin Server : 
--------------
-> It is used for distributed tracing of our request.
-> It provides User interface to access APIs Execution details. 
Ex : 
-> how much time taking to process one request.
-> which microservice taking more time to process request.
-> How many microservices invloved in one request processing.

Config Server :
-------------

-> It is used to seperate Application code or application properties.
-> It is used to externlize config properties of our application.
-> It makes our application loosly coupled with properties file/yml file.

Feign Client:
-------------
-> It is used for Inter service communication.

Note : If one API communicate with another API within the same application then it is called as 
       Inter service communication.

API Gateway :
------------
-> It acts as Entry point of all backend APIs.
-> It acts as mediator between Front-end and Backend services.
-> It is Front Controller of all the microservices.
-> In In API Gateway we will write Filters and Routings.

Filter : we can perform pre-processing and post-processing logic
Routings: to forward request to particuler backend-api.

=================================================================
Steps to Develop the Service Registory Application(Eureka Server)
==================================================================

1) Create Spring boot project with below Dependency
       -Eureka server ( spring-cloud-starter-netflix-eureka-server)
       -spring dev tools

2) configure @EnableEurekaServer annotation in the boot starter class.

Eg:
package com.subh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

3)configure the below properties in the application.yml file

spring:
  application:
    name: Service-Registory-Application

server:
  port: 8761
  
eureka:
  client:
    register-with-eureka: false


Note-1 : If "Service-Registory" project run on 8761 port then clients can discover service-registory
         and will be register automatically with service-registory.

Note-2 : If service-registory running on another port then we have to register clients with 
         service-registory manually. 

4) Once application started we can access Eureka deshboard using below url
     
       URL : http://localhost:8761/

=============================================
Steps to Develop the Admin Server Application
=============================================

1) Create Spring boot project with dependency
     -Admin server(server)
     - Spring dev tools

2) Configure the @EnableAdminServer Annotation in boot starter class.

3) configure the server port in application.yml file  (optional to us)
     server:
        port: 9090
   
4) we can access the application with this url

   URL : http://localhost:9090/

=============================================
Steps to Develop the Zipkin Server Application
=============================================

This project is already available so we not need to make.

1) Download Zipkin jar files from internet.
  
    URL : https://zipkin.io/pages/quickstart.html

2) Run zipkin jar file
   
      $ java -jar <jar-name>
     Eg: java -jar zipkin-server-3.4.1-exec.jar

3) Zipkin server runs on port no 9411

4) Access zipkin server dashboard

   URL : http://127.0.0.1:9411/

=============================================
 Develop the Welcome API
=============================================

1) create the spring boot project with the following depedency
    
    -> Eureka Discovery client
    -> Admin server client
    -> Zipkin
    -> Spring web
    -> spring boot devtools
    -> Spring boot actuators

2) configure @EnableDiscoveryClient annotation on boot starter class.

3) create rest controller class.

4) configure the properties file/yml file

properties file :
-------------

spring.application.name=Proj04_WelcomeService

server.port=8081

#if the eureka service registory is not running on 8761 port then we configure
# this property and mention the port no.
eureka.client.service-url.defaultZone = http://localhost:8761/eureka/

#configure the admin cleint
spring.application.boot.admin.client.url = http://localhost:9090/

management.endpoints.web.exposure.include='*'

yml file
--------

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
management:
  endpoints:
    web:
      exposure:
        include: '*'
server:
  port: 8081
spring:
  application:
    name: Proj04_WelcomeService
  boot:
      admin:
        client:
          url: http://localhost:9090/



