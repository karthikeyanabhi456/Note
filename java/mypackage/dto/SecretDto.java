package mypackage.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

import mypackage.enumeration.Gender;

@Data
public class SecretDto
{
	Long id;
	
	String un;
	
	String pwd;
	
	String firstName;
	
	String lastName;
	
	Date dob;
	
	Gender gender;
	
	Date doc;
	
	String mobile;
	
	FeedbackDto feedbackDto;
	
	List<NoteDto> noteDtos;
	
	List<CourseDto> courseDtos;
}