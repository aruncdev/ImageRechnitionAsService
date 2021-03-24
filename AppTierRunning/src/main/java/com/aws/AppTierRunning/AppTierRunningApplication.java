package com.aws.AppTierRunning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aws.AppTierRunning.Service.AppTierServiceImplementation;
import com.aws.AppTierRunning.configuration.AppConfig;

@SpringBootApplication
public class AppTierRunningApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(AppTierRunningApplication.class, args);
		
		System.out.println("Application is running!");
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		AppTierServiceImplementation appTierServiceImplementation = context.getBean(AppTierServiceImplementation.class);
		
		appTierServiceImplementation.mainServiceFunction();
		
		context.close();
		
	}

}
