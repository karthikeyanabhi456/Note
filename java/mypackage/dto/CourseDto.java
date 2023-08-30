package mypackage.dto;

import java.util.List;

import lombok.Data;

@Data
public class CourseDto
{
	Long id;
	
	String name;
	
	Double fee;
	
	List<SecretDto> secretDtos;
}