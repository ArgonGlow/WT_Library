package main.WTLibraryApp.Book.Copy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CopyRepository extends JpaRepository<Copy, CopyPK>{
	
	// findById method for combined-id object
	Optional<Copy> findByCopyPkId(CopyPK copyPk);
	// deleteById method for combined-id object
	Optional<Copy> deleteByCopyPkId(CopyPK copyPk);
	// findById method for user-id object
	Optional<Copy> findCopyByUserId(int id);
}
