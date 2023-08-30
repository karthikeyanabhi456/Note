package mypackage.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;

import mypackage.SecretApplication;
import mypackage.model.Secret;
import mypackage.pagination.PaginationRequest;
import mypackage.response.BadRequestException;
import mypackage.response.HttpStatusCode;
import mypackage.response.Response;
import mypackage.service.SecretService;

@Log4j2
@RestController
//@ControllerAdvice
@RequestMapping("api/secret")
public class SecretController //extends ResponseEntityExceptionHandler
{
	@Autowired
	SecretService secretService;
	
	/*@ExceptionHandler(BadRequestException.class) //if exception handling using try/catch is not present @ExceptionHandler will be invoked //second priority for an exception //@ExceptionHandler is the only way to give custom error message with the help of ResponseEntity<> with custom HTTPStatusCode //gives an object
	public final ResponseEntity<Response> handleAllExceptions(BadRequestException bre, WebRequest wr)
	{
	    log.info("SecretController -> ResponseEntity -> Started");
		Response response = new Response(HttpStatusCode.INTERNAL_SERVER_ERROR);
		log.info("SecretController -> ResponseEntity -> ended");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
	}*/
	
	@GetMapping(value = "/get/{id}")
	public Object getOne(@PathVariable Long id) //use @PathVariable for every property
	{
		log.info("SecretController -> getOne -> started & ended");
		return secretService.getOne(id);
	}
	
	/*@PostMapping(value = "/save")
	public Object save(@RequestBody SecretDTO secretDTO)
	{
	    log.info("SecretController -> save -> started & ended");
		return secretService.save(secretDTO);
	}*/
	

	//@Secured({"ROLE_.CREATE", "ROLE_.MODIFY"})
	@PutMapping(value = "/update") //@Post and @Put are almost same
	public Object update(@RequestBody Secret secret) //save and update can be made to use the same function by using a condition
	{
		log.info("SecretController -> update -> started & ended");
		return secretService.update(secret);
	}
	
	//@Secured({"ROLE_.DELETE"})
	@DeleteMapping(value = "/delete/{id}")
	public Object delete(@PathVariable Long id)
	{
		log.info("SecretController -> delete -> started & ended");
		return secretService.delete(id);
	}
	
	@PostMapping(value = "/signin")
	public Object signIn(@RequestBody Secret secret)
	{
		log.info("SecretController -> signin -> started & ended");
		return secretService.signIn(secret);
	}
	
	/*@GetMapping(value = "/download/excel/{id}")
	public Object downloadExcel(@PathVariable Long id, HttpServletResponse response)
	{
	    log.info("SecretController -> downloadExcel -> started & ended");
		return secretService.downloadExcel(id, response);
	}*/
	
	/*@GetMapping(value = "/upload/excel/{id}")
	public Object uploadExcel(@PathVariable Long id, byte[] file)
	{
	    log.info("SecretController -> uploadExcel -> started & ended");
		return secretService.uploadExcel(id, file);
	}*/
	
	/*@GetMapping(value = "/generate/pdf/{id}")
	public Object generatePdf(@PathVariable Long id, byte[] file)
	{
	    log.info("SecretController -> generatePdf -> started & ended");
		return secretService.generatePdf(id, file);
	}*/
	
	/*@DeleteMapping(value = "/delete/all")
	public Object deleteAll()
	{
	    log.info("SecretController -> deleteAll -> started & ended");
		secretService.deleteAll();
	}*/
	
	@PostMapping(value = "/restart") //same as deleteAll
	public Object restart(@RequestBody Secret secret)
	{
		log.info("SecretController -> restart -> started & ended");
		return secretService.restart(secret);
	}
	//getOne
	//getAll
	//save
	//update
	//Page
	//delete
	//deleteAll
}