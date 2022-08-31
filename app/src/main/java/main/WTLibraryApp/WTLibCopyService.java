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
	
	public Optional<Copies> findCopy(long id) {
		return repo.findById(id);
	}
	
	public void deleteCopy(long id) {
		repo.deleteById(id);
	}
	
	public void updateCopy(Copies copy) {
		repo.save(copy);
	}
}
