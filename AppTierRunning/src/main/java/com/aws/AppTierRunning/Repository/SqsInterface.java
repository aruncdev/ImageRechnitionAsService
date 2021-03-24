package com.aws.AppTierRunning.Repository;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.Message;

public interface SqsInterface {
	
	public CreateQueueResult createSqsQueue(String sqsQueueName);
	
	public void deleteMessage(Message message, String sqsQueueName);
	
	public Message receiveMessage(String sqsQueueName, Integer waitTime, Integer visibilityTimeout);
	
	public void sendMessage(String messageBody, String sqsQueueName, Integer delaySeconds);
	
	public String DeepLearning(String urlInput);
}
