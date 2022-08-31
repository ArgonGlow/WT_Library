package main.WTLibraryApp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWTLibBookRepositrory extends JpaRepository<Books, Long>{
/*
 * performs the query and searches for title keyword when
 * function is called
 * inputs title
 */
	
    List<Books> findByTitleIgnoreCaseContainingOrIsbnIgnoreCaseContainingOrAuthorIgnoreCaseContaining(String title, String isbn, String author);
   
}

