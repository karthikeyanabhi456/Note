package mypackage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mypackage.model.Note;
import mypackage.model.Secret;
import mypackage.pagination.PaginationRequest;
import mypackage.pagination.PaginationResponse;
import mypackage.repository.NoteRepository;
import mypackage.repository.SecretRepository;
import mypackage.response.BadRequestException;
import mypackage.response.HttpStatusCode;
import mypackage.response.Response;
import mypackage.service.impl.SecretServiceImpl;

@Log4j2
@Service
public class NoteService
{
	@Autowired
	NoteRepository noteRepo;
	
	@Autowired
	SecretRepository secretRepo;
	
	public Object getOne(Long id)
	{
		log.info("NoteService -> getOne -> started");
		Response response = new Response();
		Note note = noteRepo.getOne(id);
		response.setMsg(HttpStatusCode.SUCCESS, note);
		log.info("NoteService -> getOne -> ended");
		return response;
	}
	
	/*public Object save(Long id, Note note)
	{
		log.info("NoteService -> save -> started");
		Response response = new Response();
		Secret secret = secretRepo.getOne(id);
		note.setSecret(secret);
		noteRepo.save(note);
		response.setMsg(HttpStatusCode.SUCCESS);
		log.info("NoteService -> save -> ended");
		return response;
	}*/
	
	/*public Object delete(Long id)
	{
		log.info("NoteService -> delete -> started");
		Response response = new Response();
		Note note = noteRepo.getOne(id);
		noteRepo.delete(note);
		response.setMsg(HttpStatusCode.SUCCESS);
		log.info("NoteService -> delete -> ended");
		return response;
	}*/
	
	public Object pagination(Long id, PaginationRequest paginationRequest)
	{
		log.info("NoteService -> pagination -> started");
		Response response = new Response();
		PaginationResponse paginationResponse = new PaginationResponse();
		
		Pageable pageable = null;
		
		int size = paginationRequest.getSize();
		int page = paginationRequest.getPage();
		String properties = paginationRequest.getProperties(); //sort field
		String direction = paginationRequest.getDirection();
		String searchKey = paginationRequest.getSearchKey();
		
		searchKey = searchKey.trim();
		
		Direction asc = Sort.Direction.ASC;
		Direction desc = Sort.Direction.DESC;
		
		if(direction == null)
			pageable = PageRequest.of(page, size); //page and size starts with 1
		else if(direction.equals("ASC"))
			pageable = PageRequest.of(page, size, asc, properties); //asc or desc and properties denotes the sortOrder by one sortField
		else if(direction.equals("DESC"))
			pageable = PageRequest.of(page, size, desc, properties);
		
		Page<Note> notePage = noteRepo.noteBySecret(id, searchKey, pageable);
		
		List<Object> notes = new ArrayList<Object>();
		for(Note note : notePage.getContent())
		{
			notes.add(note);
		}
		paginationResponse.setContent(notes);
		paginationResponse.setNumberOfElements(notePage.getNumberOfElements());
		paginationResponse.setTotalPages(notePage.getTotalPages());
		paginationResponse.setTotalElements(notePage.getTotalElements());
		response.setMsg(HttpStatusCode.SUCCESS, paginationResponse);
		log.info("NoteService -> pagination -> ended");
		return response;
	}
}