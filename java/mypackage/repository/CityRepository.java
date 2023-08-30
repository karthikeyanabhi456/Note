package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.City;

public interface CityRepository extends JpaRepository<City, Long>
{
	
}