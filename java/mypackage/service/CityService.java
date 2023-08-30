package mypackage.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mypackage.model.City;
import mypackage.repository.CityRepository;
import mypackage.response.BadRequestException;
import mypackage.response.HttpStatusCode;
import mypackage.response.Response;
import mypackage.service.impl.SecretServiceImpl;

@Log4j2
@Service
public class CityService
{
	@Autowired
	CityRepository cityRepo;
	
	public Object getAll()
	{
		log.info("CityService -> getAll -> started");
		Response response = new Response();
		List<City> cities = cityRepo.findAll();
		response.setMsg(HttpStatusCode.SUCCESS, cities);
		log.info("CityService -> getAll -> ended");
		return response;
	}
	
	@Transactional
	public Object updates(List<City> cities)
	{
		log.info("CityService -> updates -> started");
		Response response = new Response();
		for(City city : cities)
		{
			cityRepo.save(city);
		}
		//city = Converter.toCity(cityDto)
		response.setMsg(HttpStatusCode.SUCCESS);
		log.info("CityService -> updates -> ended");
		return response;
	}
	
	@Transactional
	public Object deletes(List<Long> ids)
	{
		log.info("CityService -> deletes -> started");
		Response response = new Response();
		for(Long id : ids)
		{
			cityRepo.deleteById(id);
		}
		response.setMsg(HttpStatusCode.SUCCESS);
		log.info("CityService -> deletes -> ended");
		return response;
	}
}