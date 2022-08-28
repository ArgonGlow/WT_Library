package main.WTLibraryApp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WTLibBookService {
	
	@Autowired
	private IWTLibBookRepositrory repo;
	
	public List<Books> allBooks(){
		return repo.findAll();
	}
	
	public Optional<Books> findBook(long id) {
		return repo.findById(id);
	}
	
	public void createBook(Books book) {
		repo.save(book);
	}
	
	public void deleteBook(long id) {
		repo.deleteById(id);
	}
}
