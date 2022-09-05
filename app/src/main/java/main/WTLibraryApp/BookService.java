package main.WTLibraryApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	private IBookRepository repo;

	public List<Books> findAll() {
		return repo.findAll();
	}
	
	public void saveBook(Books book) {
		repo.save(book);
	}
	
	public Books find(long id) {
		Books book = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
		return book;
	}
	
	public void delete(Books book) {
		repo.delete(book);
	}
	
}
