package main.WTLibraryApp.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import main.WTLibraryApp.User.User;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
/*
 * performs the query and searches for title keyword when
 * function is called
 * inputs title
 */

    List<Book> findByTitleIgnoreCaseContainingOrIsbnIgnoreCaseContainingOrAuthorIgnoreCaseContaining(String title, String isbn, String author);
}

