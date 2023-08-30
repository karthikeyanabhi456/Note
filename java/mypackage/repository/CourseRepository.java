package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mypackage.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>
{
	
}