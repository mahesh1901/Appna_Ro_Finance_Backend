package com.finance.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AppnaRoFinanceServerApplication {

	public static void main(String[] args) {
		System.out.println("Appna_Ro_Finance  Application Server");
		SpringApplication.run(AppnaRoFinanceServerApplication.class, args);
	}

}
