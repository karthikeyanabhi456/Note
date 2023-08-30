package mypackage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mypackage.model.Feedback;
import mypackage.model.Secret;
import mypackage.repository.FeedbackRepository;
import mypackage.response.BadRequestException;
import mypackage.response.HttpStatusCode;
import mypackage.response.Response;
import mypackage.service.impl.SecretServiceImpl;

@Log4j2
@Service
public class FeedbackService
{	
	@Autowired
	FeedbackRepository feedbackRepo;
	
	public Object feedbackUn()
	{
		log.info("FeedbackService -> feedbackUn -> started");
		Response response = new Response();
		List<Object> objects = feedbackRepo.feedbackUn(); 
		response.setMsg(HttpStatusCode.SUCCESS, objects);
		log.info("FeedbackService -> feedbackUn -> ended");
		return response; 
	}
	
	/*public void save() //this method can also be used but calling only via commandLineRunner
	{
	    log.info("FeedbackService -> save -> started");
		Feedback feedback = new Feedback();
		feedback.setId(1L);
		feedback.setMsg("Good");
		feedbackRepo.save(feedback);
		log.info("FeedbackService -> save -> ended"); 
	}*/
}