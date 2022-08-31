package main.WTLibraryApp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWTLibCopyRepository extends JpaRepository<Copies, CopiesId>{
	
	Optional<Copies> findByCopiesId(CopiesId copiesId);
	Optional<Copies> deleteByCopiesId(CopiesId copiesId);
}
