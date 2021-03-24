package com.aws.AppTierRunning.Repository;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.aws.AppTierRunning.configuration.AwsConfiguration;
import com.aws.AppTierRunning.configuration.Constants;



public class S3Implementation implements S3Interface{
	
	Bucket returnBucket = null;
	
	@Autowired
	private AwsConfiguration awsConfiguration;
	
	@Override
    public Bucket createBucket(String bucketName) {
    	
    	if (awsConfiguration.amazonS3().doesBucketExistV2(bucketName)) {
    		returnBucket = getBucket(bucketName);
		} else {
			returnBucket = awsConfiguration.amazonS3().createBucket(new CreateBucketRequest(bucketName));
		}

    	
    	return returnBucket;
	
    }
	
	@Override
	public Bucket getBucket(String bucketName) {
		
		List<Bucket> buckets = awsConfiguration.amazonS3().listBuckets();
		
		for (Bucket bucket : buckets) {
			if (bucket.getName().equals(bucketName)) {
				returnBucket = bucket;
			}
		}
		
		
		return returnBucket;
		
	}
	
	@Override
	public void putObject(String key, String value) {
		
		this.createBucket(Constants.OUTPUT_BUCKET_NAME);
		
		byte[] contentAsBytes = null;
		
		try {
			contentAsBytes = value.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		ByteArrayInputStream contentsAsStream = new ByteArrayInputStream(contentAsBytes);
		
		ObjectMetadata md = new ObjectMetadata();
		
		md.setContentLength(contentAsBytes.length);
		
		awsConfiguration.amazonS3().putObject(new PutObjectRequest(Constants.OUTPUT_BUCKET_NAME, key, contentsAsStream, md));
		
	}

}
