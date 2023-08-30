package mypackage.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import mypackage.model.Role;
import mypackage.repository.RoleRepository;
import mypackage.response.BadRequestException;
import mypackage.response.HttpStatusCode;
import mypackage.response.Response;
import mypackage.service.impl.SecretServiceImpl;

@Log4j2
@Service
public class RoleService
{
	@Autowired
	RoleRepository roleRepo;
	
	public Object getAll()
	{
		log.info("RoleService -> getAll -> started");
		Response response = new Response();
		List<Role> roles = roleRepo.findAll();
		response.setMsg(HttpStatusCode.SUCCESS, roles);
		log.info("RoleService -> getAll -> ended");
		return response;
	}
	
	public Object update(Role role)
	{
		log.info("RoleService -> update -> started");
		Response response = new Response();
		roleRepo.save(role);
		//role = Converter.toRole(roleDto)
		response.setMsg(HttpStatusCode.SUCCESS);
		log.info("RoleService -> update -> ended");
		return response;
	}
	
	public Object delete(Long id)
	{
		log.info("RoleService -> delete -> started");
		Response response = new Response();
		roleRepo.deleteById(id);
		response.setMsg(HttpStatusCode.SUCCESS);
		log.info("RoleService -> delete -> ended");
		return response;
	}
}