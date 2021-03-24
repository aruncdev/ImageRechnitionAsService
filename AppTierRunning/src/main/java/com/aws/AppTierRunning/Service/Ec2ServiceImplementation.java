package com.aws.AppTierRunning.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.AppTierRunning.Repository.Ec2Interface;

@Service
public class Ec2ServiceImplementation implements Ec2ServiceInterface {
	
	@Autowired
	private Ec2Interface ec2Repository;

	@Override
	public void endInstance() {
		// TODO Auto-generated method stub
		ec2Repository.endInstance();
	}

}
