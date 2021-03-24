package com.aws.AppTierRunning.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.aws.AppTierRunning.Repository.S3Interface;

public class S3ServiceImplementation implements S3ServiceInterface {
	
	@Autowired
	private S3Interface s3Repository;

	@Override
	public void putObject(String key, String value) {
		// TODO Auto-generated method stub
		
		s3Repository.putObject(key, value);

	}

}
