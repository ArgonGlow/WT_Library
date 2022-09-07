package main.WTLibraryApp.Book.Copy;

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
	
	public Optional<Copy> findCopyByUserId(int id) {
		return repo.findCopyByUserId(id);
	}
	
	public Optional<Copy> findCopy(CopyPK copyPk) {
		return repo.findByCopyPkId(copyPk);
	} 
	
	public void deleteCopy(CopyPK copyPk) {
		repo.deleteByCopyPkId(copyPk);
	}
	
	public void updateCopy(Copy copy) {
		repo.save(copy);
	}
	
}
