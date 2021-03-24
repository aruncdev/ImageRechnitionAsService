package com.aws.AppTierRunning.Service;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.Message;

public interface SqsServiceInterface {
	
    public CreateQueueResult createSqsQueue(String sqsQueueName);
	
	void deleteMessage(Message message, String sqsQueueName);
	
	public Message receiveMessage(String sqsQueueName, Integer waitTime, Integer visibilityTimeout);
	
	public void sendMessage(String messageBody, String sqsQueueName, Integer delaySeconds);

	public String deepLearningOutput(String imageName);
}
