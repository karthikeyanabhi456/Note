package mypackage.service;

import java.util.List;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import mypackage.model.Secret;
import mypackage.model.Course;
import mypackage.repository.CourseRepository;
import mypackage.repository.SecretRepository;
import mypackage.response.BadRequestException;
import mypackage.response.HttpStatusCode;
import mypackage.response.Response;
import mypackage.service.impl.SecretServiceImpl;

@Log4j2
@Service
public class CourseService
{
	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	SecretRepository secretRepo;
	
	public Object getAll()
	{
		log.info("CourseService -> getAll -> started");
		Response response = new Response();
		List<Course> courses = courseRepo.findAll();
		response.setMsg(HttpStatusCode.SUCCESS, courses);
		log.info("CourseService -> getAll -> ended");
		return response;
	}
	
	public Object update(Course course)
	{
		log.info("CourseService -> update -> started");
		Response response = new Response();
		List<Secret> secrets = new ArrayList<Secret>();
		//Secret secret = new Secret();
		//Course course = new Course();
		/*for(Secret secret : course.getSecrets())
		{
			//secret.setCourses(courses);
			secrets.add(secret);
		}
		course.setSecrets(secrets);*/
		//secretRepository.save(secret);
		courseRepo.save(course);
		//course = Converter.toCourse(courseDto)
		response.setMsg(HttpStatusCode.SUCCESS);
		log.info("CourseService -> update -> ended");
		return response;
	}
	
	public Object delete(Long id)
	{
		log.info("CourseService -> delete -> started");
		Response response = new Response();
		courseRepo.deleteById(id);
		response.setMsg(HttpStatusCode.SUCCESS);
		log.info("CourseService -> delete -> ended");
		return response;
	}
}