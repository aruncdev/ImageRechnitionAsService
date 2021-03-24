package com.aws.AppTierRunning.Repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.util.EC2MetadataUtils;
import com.aws.AppTierRunning.configuration.AwsConfiguration;



public class Ec2Implementation implements Ec2Interface{
	
	@Autowired
	private AwsConfiguration awsConfiguration;
	
	public void endInstance() {
		
		String instanceId = EC2MetadataUtils.getInstanceId();
		
		TerminateInstancesRequest terminatRequest = new TerminateInstancesRequest().withInstanceIds(instanceId);
		
		awsConfiguration.amazonEC2().terminateInstances(terminatRequest);
	}

}
