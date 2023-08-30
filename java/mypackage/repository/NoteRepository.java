package mypackage.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mypackage.model.Note;

public interface NoteRepository extends JpaRepository<Note, Long>
{
	@Query("SELECT n FROM Note n WHERE n.secret.id = :id AND (:searchKey is null OR LOWER(n.msg) LIKE LOWER(CONCAT(:searchKey || '%')))") //read from innermost bracket
	Page<Note> noteBySecret(@Param("id") Long id, @Param("searchKey") String searchKey, Pageable pageable);
}