package main.WTLibraryApp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWTLibCopyRepository extends JpaRepository<Copies, Long>{
	Optional<Copies> findByBookIdCopyId(long bookId, long copyId);
}
