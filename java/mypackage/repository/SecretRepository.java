package mypackage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mypackage.model.Secret;

public interface SecretRepository extends JpaRepository<Secret, Long>
{
	//JPA query with numbered parameters //recommended
	@Query("SELECT s FROM Secret s WHERE s.un = ?1 AND s.pwd = ?2") //first alphabet is enough for entity class for readability
	Secret signIn(String un, String pwd);
	
	//native query
	@Query(value = "SELECT * FROM secret WHERE un = :un", nativeQuery = true) //value and native query has to be included
	Secret checkUserName(@Param("un") String un);
	
	@Query("SELECT s FROM Secret s WHERE s.un = ?1 AND s.pwd = ?2 AND s.id = (SELECT MIN(s.id) FROM Secret s)") //second query is must to be enclosed in brackets
	Secret reset(String un, String pwd);
}