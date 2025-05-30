package br.com.fiap.service_discorvery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceDiscorveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceDiscorveryApplication.class, args);
	}

}
