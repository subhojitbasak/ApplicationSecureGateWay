package com.form.login.demo;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
public class ApplicationSecureGateway {



	public static void main(String[] args) {

		SpringApplication.run(ApplicationSecureGateway.class, args);
	}

}
