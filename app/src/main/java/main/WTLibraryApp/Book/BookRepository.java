package main.WTLibraryApp.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.WTLibraryApp.Book.Copy.Copy;
import main.WTLibraryApp.User.User;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
/*
 * performs the query and searches for title keyword when
 * function is called
 * inputs title
 */

    List<Book> findByTitleIgnoreCaseContainingOrIsbnIgnoreCaseContainingOrAuthorIgnoreCaseContaining(String title, String isbn, String author);

	@Query(value = "SELECT * FROM books WHERE book_id IN \r\n"
			+ "(SELECT book_id FROM copies WHERE loaned_by_user LIKE %:userId%)", nativeQuery = true)
	List<Book> findBookByUserId(long userId);
	
	@Query(value = "SELECT * FROM books WHERE book_id IN\r\n"
			+ "(SELECT book_id FROM reservations WHERE user_id LIKE %:userId%)", nativeQuery = true)
	List<Book> findBookByReservationUserId(long userId);
}

 