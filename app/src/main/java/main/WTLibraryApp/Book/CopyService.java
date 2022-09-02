package main.WTLibraryApp.Book;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopyService {
	
	@Autowired
	private CopyRepository repo;
	
	public List<Copy> allCopies(){
		return repo.findAll();
	}
	
	public Optional<Copy> findCopy(CopyPK copiesPk) {
		return repo.findByCopiesId(copiesPk);
	}
	
	public void deleteCopy(CopyPK copiesPk) {
		repo.deleteByCopiesId(copiesPk);
	}
	
	public void updateCopy(Copy copy) {
		repo.save(copy);
	}
	
}
