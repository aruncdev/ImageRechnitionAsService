package com.aws.AppTierRunning.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.model.Message;
import com.aws.AppTierRunning.configuration.Constants;

@Service
public class AppTierServiceImplementation implements AppTierServiceInterface {
	
	@Autowired
	private SqsServiceInterface sqsService;
	
	@Autowired
	private S3ServiceInterface s3Service;
	
	@Autowired
	private Ec2ServiceInterface ec2Service;

	@Override
	public void mainServiceFunction() {
		// TODO Auto-generated method stub
		
//		int count = 0;
		
		while (true) {
			
			Message message = sqsService.receiveMessage(Constants.REQUEST_QUEUE, 20, 50);
			
//			int inputQueueLength = sqsService.getMessageCount(Constants.REQUEST_QUEUE, 20, 50);
			
			if (message == null) {
				break;
			}
			
			String imageName = message.getBody();
			
			String classifierName = sqsService.deepLearningOutput(imageName);
			
			if (classifierName == null) {
				classifierName = "NotIdentified";
			}
			
			classifierName = classifierName.trim();
			
			String arr[] = imageName.split("\\.");
			
			if(arr!=null)
			s3Service.putObject(arr[0], arr[0]+", "+ classifierName);
			
			//s3Service.putObject(imageName, classifierName);
			
			//s3Service.putObject("cat", "dog");
			
			//System.out.println(imageName + "|" + classifierName);
		
			sqsService.sendMessage(imageName + "|" + classifierName, Constants.RESPONSE_QUEUE, 0);
			
			sqsService.deleteMessage(message, Constants.REQUEST_QUEUE);
		}
		
		ec2Service.endInstance();

	}

}
