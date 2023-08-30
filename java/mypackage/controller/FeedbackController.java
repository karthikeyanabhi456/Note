package mypackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import mypackage.model.Feedback;
import mypackage.service.FeedbackService;
import mypackage.service.impl.SecretServiceImpl;

@Log4j2
@RestController
@ControllerAdvice
@RequestMapping("/api/secret/feedback")
public class FeedbackController
{
	@Autowired
	FeedbackService feedbackService;
	
	@GetMapping(value = "/unsfeedback")
	public Object feedbackUn()
	{
		log.info("FeedbackController -> feedbackUn -> started & ended");
		return feedbackService.feedbackUn();
	}
}