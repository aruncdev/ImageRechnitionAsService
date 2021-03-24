package com.aws.AppTierRunning.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.Message;
import com.aws.AppTierRunning.Repository.SqsInterface;

@Service
public class sqsServiceImplementation implements SqsServiceInterface {
	
	@Autowired
	private SqsInterface sqsRepository;

	@Override
	public CreateQueueResult createSqsQueue(String sqsQueueName) {
		// TODO Auto-generated method stub
		return sqsRepository.createSqsQueue(sqsQueueName);
	}

	@Override
	public void deleteMessage(Message message, String sqsQueueName) {
		// TODO Auto-generated method stub
		sqsRepository.deleteMessage(message, sqsQueueName);
	}

	@Override
	public Message receiveMessage(String sqsQueueName, Integer waitTime, Integer visibilityTimeout) {
		// TODO Auto-generated method stub
		return sqsRepository.receiveMessage(sqsQueueName, waitTime, visibilityTimeout);
	}

	@Override
	public void sendMessage(String messageBody, String sqsQueueName, Integer delaySeconds) {
		// TODO Auto-generated method stub
		sqsRepository.sendMessage(messageBody, sqsQueueName, delaySeconds);
	}

	@Override
	public String deepLearningOutput(String imageName) {
		// TODO Auto-generated method stub
		return sqsRepository.DeepLearning(imageName);
	}
}
