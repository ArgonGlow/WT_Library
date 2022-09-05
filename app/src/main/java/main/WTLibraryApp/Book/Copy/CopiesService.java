package main.WTLibraryApp.Book.Copy;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CopiesService {
	
	@Autowired
	private CopiesRepository repo;
	
	public List<Copies> allCopies(){
		return repo.findAll();
	}
	
	public Optional<Copies> findCopy(CopiesPK copiesPk) {
		return repo.findByCopiesId(copiesPk);
	}
	
	public void deleteCopy(CopiesPK copiesPk) {
		repo.deleteByCopiesId(copiesPk);
	}
	
	public void updateCopy(Copies copy) {
		repo.save(copy);
	}
	
}
