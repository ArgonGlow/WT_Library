package main.WTLibraryApp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WTLibCopyService {
	
	@Autowired
	private IWTLibCopyRepository repo;
	
	public List<Copies> allCopies(){
		return repo.findAll();
	}
	
	public Optional<Copies> findCopy(CopiesId copiesId) {
		return repo.findByCopiesId(copiesId);
	}
	
	public void deleteCopy(CopiesId copiesId) {
		// repo.deleteById(id);
		repo.deleteByCopiesId(copiesId);
	}
	
	public void updateCopy(Copies copy) {
		repo.save(copy);
	}
	
}
