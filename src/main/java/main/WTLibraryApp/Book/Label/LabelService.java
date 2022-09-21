package main.WTLibraryApp.Book.Label;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.WTLibraryApp.Book.Book;
import main.WTLibraryApp.Book.BookService;

@Service
public class LabelService {
	
	@Autowired
	private LabelRepository repo;
	
	public List<Label> allLabels(){
		return repo.findAll();
	}
	
	public void addLabel(Label label) {
		repo.save(label);
	}
	
	public void updateLabel(Label label) {
		repo.save(label);
	}
	
	public Optional<Label> find(long id) {
		return repo.findById(id);
	}
	
	public void deleteLabel(Label label) {
		repo.deleteRelation(label.getId());
		repo.delete(label);
	}
}
