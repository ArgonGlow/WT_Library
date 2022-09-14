package main.WTLibraryApp.Book.Copy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import main.WTLibraryApp.Book.Book;


@Repository
public interface CopyRepository extends JpaRepository<Copy, Long>{
	
// deleteById method for combined-id object
//	Copy deleteByCopyPkId(CopyPK copyPk);
	
	// findById method for user-id object
	List<Copy> findCopyByUserId(Long id);

	List<Copy> findByBookAndVersion(Book book, int version);

	List<Copy> findByBook(Book book);

//	//find copies by bookId
//	@Query(value = "select * from copies where book_id like :id", nativeQuery = true)
//	List<Copy> findCopyByBookId(@Param("id") Long id);
	
	//find all copies of titles one user reserved
	@Query(value = "SELECT * FROM copies WHERE book_id IN\r\n"
			+ "(SELECT book_id FROM reservations WHERE user_id LIKE :userId)", nativeQuery = true)
	List<Copy> findCopyByReservationUserId(Long userId);
	
}
 