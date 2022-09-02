package main.WTLibraryApp.Book;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CopyRepository extends JpaRepository<Copy, CopyPK>{
	
	// findById method for combined-id object
	Optional<Copy> findByCopiesId(CopyPK copiesPk);
	// deleteById method for combined-id object
	Optional<Copy> deleteByCopiesId(CopyPK copiesPk);
}
