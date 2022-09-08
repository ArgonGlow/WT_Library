package main.WTLibraryApp.Book.Copy;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.WTLibraryApp.User.User;


@Repository
public interface CopyRepository extends JpaRepository<Copy, CopyPK>{
	
	// findById method for combined-id object
	List<Copy> findByCopyPkId(CopyPK copyPk);
	// deleteById method for combined-id object
//	Copy deleteByCopyPkId(CopyPK copyPk);
	// findById method for user-id object
	List<Copy> findCopyByUserId(int id);
	
	@Query(value = "select * from copies where book_id like %:id%", nativeQuery = true)
	List<Copy> findCopyByBookId(@Param("id") long id);
}
 