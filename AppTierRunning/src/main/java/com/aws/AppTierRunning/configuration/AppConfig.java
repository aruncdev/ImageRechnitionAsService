package com.aws.AppTierRunning.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.aws.AppTierRunning.Repository.Ec2Implementation;
import com.aws.AppTierRunning.Repository.Ec2Interface;
import com.aws.AppTierRunning.Repository.S3Implementation;
import com.aws.AppTierRunning.Repository.S3Interface;
import com.aws.AppTierRunning.Repository.SqsImplementation;
import com.aws.AppTierRunning.Repository.SqsInterface;
import com.aws.AppTierRunning.Service.AppTierServiceImplementation;
import com.aws.AppTierRunning.Service.AppTierServiceInterface;
import com.aws.AppTierRunning.Service.Ec2ServiceImplementation;
import com.aws.AppTierRunning.Service.Ec2ServiceInterface;
import com.aws.AppTierRunning.Service.S3ServiceImplementation;
import com.aws.AppTierRunning.Service.S3ServiceInterface;
import com.aws.AppTierRunning.Service.SqsServiceInterface;
import com.aws.AppTierRunning.Service.sqsServiceImplementation;



@Configuration
public class AppConfig {
	
	@Bean
	public AppTierServiceInterface appTierService() {
		return new AppTierServiceImplementation();
	}

	@Bean
	public SqsServiceInterface sqsService() {
		return new sqsServiceImplementation();
	}

	@Bean
	public SqsInterface sqsRepo() {
		return new SqsImplementation();
	}

	@Bean
	public S3Interface s3Repo() {
		return new S3Implementation();
	}

	@Bean
	public S3ServiceInterface s3Service() {
		return new S3ServiceImplementation();
	}

	@Bean
	public Ec2Interface ec2Repo() {
		return new Ec2Implementation();
	}

	@Bean
	public Ec2ServiceInterface ec2Service() {
		return new Ec2ServiceImplementation();
	}

	@Bean
	public AwsConfiguration awsConfiguration() {
		return new AwsConfiguration();
	}

}
