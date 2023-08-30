package mypackage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mypackage.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>
{	
	@Query("SELECT f.secret.un, f.msg FROM Feedback f")
	List<Object> feedbackUn();
}