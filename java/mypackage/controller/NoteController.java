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
import mypackage.pagination.PaginationRequest;
import mypackage.service.NoteService;
import mypackage.service.impl.SecretServiceImpl;

@Log4j2
@RestController
@ControllerAdvice
@RequestMapping("/api/secret/note")
public class NoteController
{
	@Autowired
	NoteService noteService;
	
	/*@GetMapping(value = "/get/{id}")
	public Object getOne(@PathVariable Long id)
	{
		log.info("NoteController -> get -> started & ended");
		return noteService.getOne(id);
	}*/
	
	/*@PostMapping(value = "/pagination")
	public Object pagination(@RequestBody PaginationRequest paginationRequest)
	{
	    log.info("NoteController -> pagination -> started & ended");
		return secretService.pagination(paginationRequest);
	}*/
	
	@PostMapping(value = "/pagination/{id}")
	public Object paginationSet(@PathVariable Long id, @RequestBody PaginationRequest paginationRequest)
	{
		log.info("NoteController -> pagination -> started & ended");
		return noteService.pagination(id, paginationRequest);
	}
	
	/*@DeleteMapping(value = "/delete/{id}")
	public Object delete(@PathVariable Long id)
	{
		log.info("NoteController -> delete -> started & ended");
		return noteService.delete(id);
	}*/
}