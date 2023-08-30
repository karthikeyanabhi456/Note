package mypackage.service;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import mypackage.model.Secret;
import mypackage.model.Feedback;
import mypackage.model.Note;
import mypackage.pagination.PaginationRequest;
import mypackage.pagination.PaginationResponse;
import mypackage.repository.CityRepository;
import mypackage.repository.CourseRepository;
import mypackage.repository.FeedbackRepository;
import mypackage.repository.NoteRepository;
import mypackage.repository.RoleRepository;
import mypackage.repository.SecretRepository;
import mypackage.response.BadRequestException;
import mypackage.response.HttpStatusCode;
import mypackage.response.Response;
import mypackage.service.impl.SecretServiceImpl;
import mypackage.util.Converter;
import mypackage.util.ValidateUtil;

//import org.apache.commons.lang3.exception.ExceptionUtils;

@Log4j2
@Service
public class SecretService
{
	@Autowired
	SecretRepository secretRepo;
	
	@Autowired
	FeedbackRepository feedbackRepo;
	
	@Autowired
	NoteRepository noteRepo;
	
	@Autowired
	CityRepository cityRepo;
	
	@Autowired
	CourseRepository courseRepo;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	SecretServiceImpl secretServiceImpl;
	
	public Object getOne(Long id) //public Secret getOne() can also be used as return but without using Response class
	{
		log.info("SecretService -> getOne -> started");
		Response response = new Response();
		//ValidateUtil.throwExcIfNull(id, HttpStatusCode.INVALID); //rare exception not recommended
		Object object = secretRepo.findById(id); //findById() itself gives null (methods return type is Optional) without any exception if Id is not found
		/*try
		{
			ValidateUtil.throwExcIfNull(object, HttpStatusCode.NULL); //throwExcIfNull() throws exception as true if null
		}
		catch(BadRequestException bre)
		{
			response.setMsg(bre);
			//return new ExceptionResponse(bre.getHttpStatusCode());
			log.error("BadRequestException", bre);
			return response;
		}
		catch(Exception e)
		{
			response.setMsg(HttpStatusCode.INTERNAL_SERVER_ERROR);
			log.error("Exception", e);
			return response;
		}*/
		Secret secret = secretRepo.getOne(id); //repository methods are outside the try block for debugging //getOne() itself throws exception if Id is not found
		//SecretDto secretDto = Converter.toSecretDto(secret);
		response.setMsg(HttpStatusCode.SUCCESS, secret);
		//return new Response(HttpStatusCode.SUCCESS, object);
		log.info("SecretService -> getOne -> ended");
		return response;
	}
	
	public Object update(Secret secret) //public void update can also be used but without any return
	{
		log.info("SecretService -> update -> started");
		Response response = new Response();
		//ValidateUtil.throwExcIfNull(id, HttpStatusCode.INVALID);
		//SecretServiceImpl secretServiceImpl = new SecretServiceImpl(); //also works
		String un = secret.getUn();
		if(secret.getId() != null)
		{
			System.out.println("secretId : "+secret.getId());
		}
		/*if(secret.getFeedback().getId() != null)
		{
			System.out.println("feedbackId : "+secret.getFeedback().getId());
		}*/
		//System.out.println(secret.getFeedback().getMsg());
		/*if(secret.getFeedback().getMsg() != null)
		{
			System.out.println("Feedback");
			Feedback feedback = new Feedback();
			//secret.getFeedback().setSecret(secret);
		    //secret.setFeedback(secret.getFeedback());
			
			System.out.println(secret);
			feedback.setSecret(secret);
			feedback.setMsg(secret.getFeedback().getMsg());
			feedbackRepo.save(feedback);
			//secret.getFeedback().setMsg(secret.getFeedback().getMsg());
		    //secret.getFeedback().setSecret(secret);
		}
		if(secret.getNotes() != null)
		{
			for(Note tmpNote : secret.getNotes())
			{
				Note note = new Note();
				note.setMsg(tmpNote.getMsg());
				note.setSecret(secret);
			}
		}*/
		try
		{
			secretServiceImpl.update(secret);
			if(secret.getId() == null)
			{
				System.out.println("New Account");
				secret.setDoc(new Date());
				Secret secret2 = secretRepo.checkUserName(un);
				ValidateUtil.throwExcIfObj(secret2, HttpStatusCode.USER); //throwExcIfObj() throws exception as true if not null
			}
		}
		catch(BadRequestException bre) //for Validation class exceptions
		{
			response.setMsg(bre);
			log.error("BadRequestException", bre);
			return response; //return has to be added in every catch because thread ends here
		}
		catch(DataIntegrityViolationException divEX)
		{
			if(divEX.getMostSpecificCause().getCause().toString().contains("UK_SECRET_UN")) //method1
			{
				response.setMsg(HttpStatusCode.USER);
			}
			else if(divEX.getMostSpecificCause().getCause().toString().contains("UK_SECRET_PWD")) //method1
			{
				response.setMsg(HttpStatusCode.USER);
			}
			/*String exceptionMessage = ExceptionUtils.getRootCause(divEX).getMessage(); //method2 //lang3 import
			if(exceptionMessage.contains("UK_SECRET_PWD"))
			{
				response.setMsg(HttpStatusCode.USER);
			}*/
			log.error("DataIntegrityViolationException", divEX);
			return response;
		}
		catch(ObjectOptimisticLockingFailureException e)
		{
			response.setMsg(HttpStatusCode.CANNOT_UPDATE_LOCKED_RECORD);
			log.error("ObjectOptimisticLockingFailureException", e);
			return response;
		}
		catch(JpaObjectRetrievalFailureException e)
		{
			response.setMsg(HttpStatusCode.CANNOT_UPDATE_DELETED_RECORD);
			log.error("JpaObjectRetrievalFailureException", e);
			return response;
		}
		catch(Exception e) //Exception has to be used to catch all uncaught exceptions if one catch is present
		{
			e.getMessage();
			e.printStackTrace();
			response.setMsg(HttpStatusCode.INTERNAL_SERVER_ERROR);
			log.error("Exception", e);
			return response;
		}
		System.out.println("A");
		secretRepo.save(secret);
		System.out.println("B");
		//secret = Converter.toSecret(secretDto)
		response.setMsg(HttpStatusCode.SUCCESS);
		//response.setContent(null); //can also be used
		log.info("SecretService -> update -> ended");
		return response;
	}
	
	public Object delete(Long id)
	{
		log.info("SecretService -> delete -> started");
		Response response = new Response();
		secretRepo.deleteById(id);
		response.setMsg(HttpStatusCode.SUCCESS);
		log.info("SecretService -> delete -> ended");
		return response;
	}
	
	public Object signIn(Secret secret)
	{
		log.info("SecretService -> signIn -> started & ended");
		Response response = new Response();
		String un = secret.getUn();
		String pwd = secret.getPwd();
		try
		{
			secretServiceImpl.update(secret);
			secret = secretRepo.signIn(un, pwd);
			ValidateUtil.throwExcIfNull(secret, HttpStatusCode.USER);
		}
		catch(BadRequestException bre)
		{
			response.setMsg(bre);
			log.error("BadRequestException", bre);
			return response;
		}
		catch(Exception e)
		{
			response.setMsg(HttpStatusCode.INTERNAL_SERVER_ERROR);
			log.error("Exception", e);
			return response;
		}
		//secretDto = Converter.toSecretDto(secret);
		response.setMsg(HttpStatusCode.SUCCESS, secret);
		log.info("SecretService -> signIn -> ended");
		return response;
	}
	
	/*public Object downloadExcel(Long id, HttpServletResponse hsr)
	{
	    log.info("SecretService -> downloadExcel -> started");
		Response response = new Response();
		try
		{
			secretServiceImpl.downloadExcel(id, hsr);
		}
		//catch(IOException ioe)
		//{
			//response.setMsg(HttpStatusCode.INTERNAL_SERVER_ERROR);
			//log.error("IOException", ioe);
			//return response;
		//}
		catch(Exception e)
		{
			response.setMsg(HttpStatusCode.INTERNAL_SERVER_ERROR);
			log.error("Exception", e);
			return response;
		}
		response.setMsg(HttpStatusCode.SUCCESS);
	    log.info("SecretService -> downloadExcel -> ended");
		return response;
	}*/
	
	/*public Object uploadExcel(Long id, byte[] file)
	{
	    log.info("SecretService -> uploadExcel -> started");
		Response response = new Response();
		try
		{
			secretServiceImpl.uploadExcel(id, file);
		}
		//catch(IOException ioe)
		//{
			//response.setMsg(HttpStatusCode.INTERNAL_SERVER_ERROR);
			//log.error("IOException", ioe);
			//return response;
		//}
		catch(Exception e)
		{
			response.setMsg(HttpStatusCode.INTERNAL_SERVER_ERROR);
			log.error("Exception", e);
			return response;
		}
		response.setMsg(HttpStatusCode.SUCCESS);
		log.info("SecretService -> uploadExcel -> started & ended");
		return response;
	}*/
	
	/*public Object generatePdf(Long id)
	{
	    log.info("SecretService -> generatePdf -> started");
		Response response = new Response();
		try
		{
			secretServiceImpl.generatePdf(id);
		}
		catch(Exception e)
		{
			response.setMsg(HttpStatusCode.INTERNAL_SERVER_ERROR);
			log.error("Exception", e);
			return response;
		}
		response.setMsg(HttpStatusCode.SUCCESS);
		log.info("SecretService -> generatePdf -> ended");
		return response;
	}*/
	
	public Object restart(Secret secret)
	{
		log.info("SecretService -> restart -> started");
		Response response = new Response();
		String un = secret.getUn();
		String pwd = secret.getPwd();
		try
		{
			secretServiceImpl.update(secret);
			Secret secret2 = secretRepo.reset(un, pwd);
			ValidateUtil.throwExcIfNull(secret2, HttpStatusCode.USER);
		}
		catch(BadRequestException bre)
		{
			response.setMsg(bre);
			log.error("BadRequestException", bre);
			return response;
		}
		catch(Exception e)
		{
			response.setMsg(HttpStatusCode.INTERNAL_SERVER_ERROR);
			log.error("Exception", e);
			return response;
		}
		roleRepo.deleteAll();
		cityRepo.deleteAll();
		courseRepo.deleteAll();
		noteRepo.deleteAll();
		feedbackRepo.deleteAll();
		secretRepo.deleteAll();
		response.setMsg(HttpStatusCode.SUCCESS);
		log.info("SecretService -> restart -> ended");
		return response;
	}
}