package mypackage.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import mypackage.model.Role;
import mypackage.service.RoleService;
import mypackage.service.impl.SecretServiceImpl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@Log4j2
@RestController
@RequestMapping("api/secret/role")
public class RoleController //server-side has to follow a proper structure to be friendly with all different type of clients
{
	@Autowired
	RoleService roleService;
	
	@GetMapping(value = "/get/all")
	public Object getAll()
	{
		log.info("RoleService -> getAll -> started & ended");
		return roleService.getAll();
	}
	
	@PutMapping(value = "/update")
	public Object update(@RequestBody Role role)
	{
		log.info("RoleService -> update -> started & ended");
		return roleService.update(role);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public Object delete(@PathVariable Long id)
	{
		log.info("RoleService -> delete -> started & ended");
		return roleService.delete(id);
	}
}