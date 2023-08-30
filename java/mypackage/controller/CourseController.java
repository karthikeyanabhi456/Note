package mypackage.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import mypackage.model.Course;
import mypackage.model.Secret;
import mypackage.service.CourseService;
import mypackage.service.SecretService;

@Log4j2
@RestController
@RequestMapping("api/secret/course")
public class CourseController
{
	@Autowired
	CourseService courseService;
	
	@GetMapping(value = "/get/all")
	public Object getAll()
	{
		log.info("CourseController -> getAll -> started & ended");
		return courseService.getAll();
	}
	
	
	@PutMapping(value = "/update")
	public Object update(@RequestBody Course course)
	{
		log.info("CourseController -> update -> started & ended");
		return courseService.update(course);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public Object delete(@PathVariable Long id)
	{
		log.info("CourseController -> delete -> started & ended");
		return courseService.delete(id);
	}
}