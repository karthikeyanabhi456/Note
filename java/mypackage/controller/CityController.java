package mypackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import mypackage.model.City;
import mypackage.service.CityService;

@Log4j2
@RestController
@RequestMapping("/api/secret/city")
public class CityController
{
	@Autowired
	CityService cityService;
	
	@GetMapping(value = "/get/all")
	public Object getAll()
	{
		log.info("CityController -> getAll -> started & ended");
		return cityService.getAll();
	}
	
	@PutMapping(value = "/updates")
	public Object updates(@RequestBody List<City> cities)
	{
		log.info("CityController -> updates -> started & ended");
		return cityService.updates(cities);
	}
	
	@DeleteMapping(value = "/deletes")
	public Object deletes(@RequestBody List<Long> ids)
	{
		log.info("CityController -> deletes -> started & ended");
		return cityService.deletes(ids);
	}
}