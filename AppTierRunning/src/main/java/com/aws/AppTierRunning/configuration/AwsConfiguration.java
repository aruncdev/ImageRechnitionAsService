package com.aws.AppTierRunning.configuration;

import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;


public class AwsConfiguration {
	
	BasicAWSCredentials credentials = null;
	
	AwsConfiguration(){
		
		credentials = new BasicAWSCredentials(Constants.ACCESSKEY, Constants.SECRETKEY);
		
	}

	public AmazonS3 amazonS3() {
		AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
				.withRegion(Constants.REGION)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
		
		return amazonS3Client;
	}

	
	
	
	public AmazonSQS amazonSQS() {
		AmazonSQS amazonSQSClient = AmazonSQSClientBuilder.standard()
				.withRegion(Constants.REGION)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
		
		return amazonSQSClient;
	}

	public AmazonEC2 amazonEC2() {
		
		AmazonEC2 amazonEC2 = AmazonEC2ClientBuilder.standard()
				.withRegion(Constants.REGION)
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
		
		return amazonEC2;
	}
	
	
}
