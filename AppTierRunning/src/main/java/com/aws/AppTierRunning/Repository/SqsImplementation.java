package com.aws.AppTierRunning.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.aws.AppTierRunning.configuration.AwsConfiguration;
import com.aws.AppTierRunning.configuration.Constants;


public class SqsImplementation implements SqsInterface {
	
	@Autowired
	private AwsConfiguration awsConfiguration;

	@Override
	public void deleteMessage(Message message, String sqsQueueName) {
		
		String queueUrl = awsConfiguration.amazonSQS().getQueueUrl(sqsQueueName).getQueueUrl();
		
		String messageReceiptHandle = message.getReceiptHandle();
		
		DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest(queueUrl, messageReceiptHandle);
		
		awsConfiguration.amazonSQS().deleteMessage(deleteMessageRequest);
	}

	@Override
	public CreateQueueResult createSqsQueue(String queueName) {
		
		CreateQueueResult createQueueResult = awsConfiguration.amazonSQS().createQueue(queueName);
		
		return createQueueResult;
	}

	@Override
	public String DeepLearning(String imageName) {
		
		String predictedClassifierName = null;
		Process p;
		
		try {
			
			    S3Object o = awsConfiguration.amazonS3().getObject(Constants.INPUT_BUCKET_NAME, imageName);
			    
			    S3ObjectInputStream s3is = o.getObjectContent();
			    
			    FileOutputStream fos = new FileOutputStream(new File(imageName));
			    
			    byte[] read_buf = new byte[1024];
			    
			    int read_len = 0;
			    
			    while ((read_len = s3is.read(read_buf)) > 0) {
			        fos.write(read_buf, 0, read_len);
			    }
			    
			    s3is.close();
			    fos.close();
			
			p = Runtime.getRuntime().exec("python3 image_classification.py "+imageName);
			
			p.waitFor();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			predictedClassifierName = br.readLine();
			
			p.destroy();
			
		} 
		
		catch (Exception e) {
			System.out.println("Exception: "+ e);
		}
		
		System.out.println("Predicted Classifier: "+ predictedClassifierName);
		
		return predictedClassifierName;
	}


	@Override
	public Message receiveMessage(String queueName, Integer waitTime, Integer visibilityTimeout) {
		
		String queueUrl = awsConfiguration.amazonSQS().getQueueUrl(queueName).getQueueUrl();
		
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
		
		receiveMessageRequest.setMaxNumberOfMessages(1);
		
		receiveMessageRequest.setWaitTimeSeconds(waitTime);
		
		receiveMessageRequest.setVisibilityTimeout(visibilityTimeout);
		
		ReceiveMessageResult receiveMessageResult = awsConfiguration.amazonSQS().receiveMessage(receiveMessageRequest);
		
		List<Message> messageList = receiveMessageResult.getMessages();
		
		if (messageList.isEmpty()) {
			return null;
		}
		
		return messageList.get(0);
	}

	@Override
	public void sendMessage(String messageBody, String queueName, Integer delaySeconds) {
		
		String queueUrl = null;
		
		try 		
		{
			queueUrl = awsConfiguration.amazonSQS().getQueueUrl(queueName).getQueueUrl();
		} 
		
		catch (QueueDoesNotExistException queueDoesNotExistException) 		
		{
			CreateQueueResult createQueueResult = this.createSqsQueue(queueName);
		}
		
		queueUrl = awsConfiguration.amazonSQS().getQueueUrl(queueName).getQueueUrl();
		
//		SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(queueUrl)
//				.withMessageBody(messageBody).withDelaySeconds(delaySeconds).withMessageGroupId("testGroup");
		
		SendMessageRequest sendMessageRequest = new SendMessageRequest().withQueueUrl(queueUrl)
				.withMessageBody(messageBody).withDelaySeconds(delaySeconds);
		
		awsConfiguration.amazonSQS().sendMessage(sendMessageRequest);
		
	}

}
