package main.WTLibraryApp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IWTLibCopyRepository extends JpaRepository<Copies, CopiesPK>{
	
	// findById method for combined-id object
	Optional<Copies> findByCopiesId(CopiesPK copiesPk);
	// deleteById method for combined-id object
	Optional<Copies> deleteByCopiesId(CopiesPK copiesPk);
}
