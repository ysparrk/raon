package com.arch.raon;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@OpenAPIDefinition(
		servers = {
				@Server(url = "https://arch-raon.com/api", description = "Default Server url")
		}
)
public class RaonApplication {


	public static void main(String[] args) {
		SpringApplication.run(RaonApplication.class, args);
	}

}
