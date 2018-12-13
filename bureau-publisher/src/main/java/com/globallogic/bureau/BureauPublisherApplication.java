package com.globallogic.bureau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class BureauPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(BureauPublisherApplication.class, args);
	}

}

