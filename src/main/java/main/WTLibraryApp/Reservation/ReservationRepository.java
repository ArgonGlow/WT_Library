package main.WTLibraryApp.Reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.WTLibraryApp.Book.Book;
import main.WTLibraryApp.User.User;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	List<Reservation> findByBookAndUser(Book book, User user);
	
	//finds book by keyword
	@Query(value = "SELECT * FROM reservations WHERE book_id IN \r\n"
			+ "(SELECT id FROM books WHERE title LIKE %:keyword%)"
			+ "OR user_id IN (SELECT id FROM users WHERE first_name LIKE %:keyword%)"
			+ "OR user_id IN (SELECT id FROM users WHERE last_name LIKE %:keyword%)", nativeQuery = true)
//    @Query(value = "select * from reservations where book_id like %:keyword% or author like %:keyword%", nativeQuery = true)
    List<Reservation> findByKeyword(@Param("keyword") String keyword);

	
}
