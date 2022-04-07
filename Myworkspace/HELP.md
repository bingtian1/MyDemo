# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.7.RELEASE/maven-plugin/)
* [Cloud LoadBalancer](https://cloud.spring.io/spring-cloud-static/spring-cloud-commons/current/reference/html/#spring-cloud-loadbalancer)
* [Cloud Bootstrap](https://spring.io/projects/spring-cloud-commons)

### Guides

The following guides illustrate how to use some features concretely:

* [Client-side load-balancing with Spring Cloud LoadBalancer](https://spring.io/guides/gs/spring-cloud-loadbalancer/)
* [Routing and Filtering](https://spring.io/guides/gs/routing-and-filtering/)
* [Client-side load-balancing with Ribbon and Spring Cloud](https://spring.io/guides/gs/client-side-load-balancing/)
* [Using Spring Cloud Gateway](https://github.com/spring-cloud-samples/spring-cloud-gateway-sample)

# Spring Cloud Netflix Maintenance Mode

The dependencies listed below are in maintenance mode. We do not recommend adding them to new projects:

* Ribbon
* Zuul

The decision to move most of the Spring Cloud Netflix projects to maintenance mode was a response to Netflix not
continuing maintenance of many of the libraries that we provided support for.

Please
see [this blog entry](https://spring.io/blog/2018/12/12/spring-cloud-greenwich-rc1-available-now#spring-cloud-netflix-projects-entering-maintenance-mode)
for more information on maintenance mode and a list of suggested replacements for those libraries.
