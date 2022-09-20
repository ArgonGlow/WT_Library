package main.WTLibraryApp.Transaction;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	//finds book by keyword
	@Query(value = "SELECT * FROM transactions WHERE "
			+ "book_id IN (SELECT id FROM books WHERE title LIKE %:keyword%)"
			+ "OR user_id IN (SELECT id FROM users WHERE first_name LIKE %:keyword%)"
			+ "OR user_id IN (SELECT id FROM users WHERE last_name LIKE %:keyword%)", nativeQuery = true)
//    @Query(value = "select * from reservations where book_id like %:keyword% or author like %:keyword%", nativeQuery = true)
    List<Transaction> findByKeyword(@Param("keyword") String keyword);
}
