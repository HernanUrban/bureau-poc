package com.globallogic.bureau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class BureauServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BureauServiceApplication.class, args);
	}
}
