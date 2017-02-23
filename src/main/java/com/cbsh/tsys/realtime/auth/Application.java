package com.cbsh.tsys.realtime.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

/**
 * Application class that that starts the spring boot application.
 * 
 * @author Klhaas
 */
@SpringBootApplication
@EnableEncryptableProperties
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
