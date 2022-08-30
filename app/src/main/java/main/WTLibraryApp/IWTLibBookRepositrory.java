package main.WTLibraryApp;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWTLibBookRepositrory extends JpaRepository<Books, Long>{
/*
 * performs the query and searches for title keyword when
 * function is called
 * inputs title
 */
    @Query(value = "SELECT * FROM books "
    		+ "WHERE title LIKE '%:keyWord%'")
    List<Books> findBookByTitle(String keyWord);
   
}

