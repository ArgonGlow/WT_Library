package main.WTLibraryApp.User;

import java.util.List;
import java.util.Optional;

import javax.activation.DataSource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.WTLibraryApp.Book.Book;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "select * from users where first_name like %:keyword% or last_name like %:keyword% or email like %:keyword%", nativeQuery = true)
	public List<User> findByKeyword(@Param("keyword") String keyword);
	
	public Optional<User> findByEmail(String email);
	
	@Query(value = "SELECT * FROM books WHERE book_id IN \r\n"
			+ "   (SELECT copy_id FROM copies WHERE loaned_by_user LIKE 1)", nativeQuery = true)
	List<Book> findBookByUserId(long userId);

}
  