package com.aws.AppTierRunning.Repository;

import com.amazonaws.services.s3.model.Bucket;

public interface S3Interface {
	
    public Bucket createBucket(String bucketName);
	
	public Bucket getBucket(String bucketName);
	
	public void putObject(String key, String value);

}
