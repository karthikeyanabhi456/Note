package mypackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import javax.transaction.Transactional;

import mypackage.model.Course;
import mypackage.model.Feedback;
import mypackage.model.Note;
import mypackage.model.Secret;
import mypackage.repository.SecretRepository;
import mypackage.service.FeedbackService;
import mypackage.repository.FeedbackRepository;
import mypackage.repository.NoteRepository;

@Log4j2 //used to create logs about program flow
@SpringBootApplication
public class SecretApplication //implements CommandLineRunner //server //if project is copied change angular.json outputPath to copied project name
{
	@Autowired
	SecretRepository secretRepo;
	
	@Autowired
	FeedbackRepository feedbackRepo;
	
	@Autowired
	NoteRepository noteRepo;
	
	public static void main(String[] args)
	{
		SpringApplication.run(SecretApplication.class, args);
		log.info("SecretApplication -> main -> started");
		/*FeedbackService feedbackService = new FeedbackService();
		feedbackService.save();*/
		/*log.info("Information"); 
		log.error("Error");
		log.warn("Warning");
		log.trace("Tracing");
		log.debug("Debugging");*/
	}
	
	/*@Transactional
	//@Override
	public void run(String...arg0) throws Exception
	{
	    log.info("SecretApplication -> run -> started");
		Secret miller = new Secret("Miller"); //constructor type //based on constructor format //recommended
		//Secret miller = new Secret(); //getter setter type //this type can also be used
		//secret.setUn("Miller");
		Secret scott = new Secret("Scott");
		
		Course mathamatics = new Course("Mathamatics");
		Course computerScience = new Course("Computer Science");
		
		List<Course> courses = new ArrayList<Course>();
		courses.add(mathamatics);
		courses.add(computerScience);
		
		miller.setCourses(courses);
		scott.setCourses(courses);
		
		secretRepo.save(miller);
		secretRepo.save(scott);
		
		//clearData(); //used to delete all data in database to make it fresh when starting up the application
		//saveData(); //used to persist entities to database
		//showData(); //used to load all records in the database and show all on console
		log.info("SecretApplication -> run -> ended");
	}*/
	
	/*@Transactional
	private void clearData()
	{
	    log.info("SecretApplication -> clearData -> started");
		secretRepo.deleteAll();
		noteRepo.deleteAll();
		feedbackRepo.deleteAll();
		log.info("SecretApplication -> clearData -> ended");
	}*/
	
	/*@Transactional
	private void saveData()
	{
	    log.info("SecretApplication -> saveData -> started");
		//Store One
		Secret secret = new Secret("Right");
		
		secret.setFeedback(new Feedback("Left", secret));
		
		List<Note> notes = new ArrayList<Note>();
		Note note1 = new Note("Plug", secret);
		Note note2 = new Note("Volley", secret);
		Note note3 = new Note("Basket", secret);
		notes.add(note1);
		notes.add(note2);
		notes.add(note3);
		secret.setNotes(notes);
		
		secretRepo.save(secret);
		
		//Store a List
		Secret secret1 = new Secret("Bat");
		Secret secret2 = new Secret("Ball");
		secretRepo.saveAll(Arrays.asList(secret1, secret2));
		
		Feedback perfect = new Feedback("Perfect", new Secret("Miller"));
		feedbackRepo.save(perfect);
		log.info("SecretApplication -> saveData -> ended");
	}*/
	
	/*@Transactional
	private void showData()
	{
	    log.info("SecretApplication -> showData -> started");
		//l or L can be used to define the long data type. E.g.findById(36l)
		List<Secret> secrets = secretRepo.findAll();
		System.out.println("=================== Secret :  ==================");
		secrets.forEach(System.out::println); //repository methods returns only the unique object address of a class hence toString() is used to convert that address into a string
		
		List<Note> notes = noteRepo.findAll();
		System.out.println("=================== Note :  ==================");
		notes.forEach(System.out::println);
		
		List<Feedback> feedbacks = feedbackRepo.findAll();
		System.out.println("=================== Feedback :  ==================");
		feedbacks.forEach(System.out::println);
		log.info("SecretApplication -> showData -> ended");
	}*/
}