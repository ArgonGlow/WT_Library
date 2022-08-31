package main.WTLibraryApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WTLibBookService {
	
	@Autowired
	private IWTLibBookRepositrory repo;
	/*
	 * find all books
	 */
	public List<Books> allBooks(){
		return repo.findAll();
	}
	/*
	 * find book by id
	 * inputs id
	 */
	public Optional<Books> findBook(long id) {
		return repo.findById(id);
	}
	/*
	 * save book changes or save new book to database
	 * inputs book properties
	 */
	public void createBook(Books book) {
		repo.save(book);
	}
	/*
	 * delete book\
	 * inputs id
	 */
	public void deleteBook(long id) {
		repo.deleteById(id);
	}
	/*
	 * find book by title, isbn or author
	 * inputs title, isbn or author
	 */
	public List<Books> searchBook(String title, String isbn, String author){
		return repo.findByTitleIgnoreCaseContainingOrIsbnIgnoreCaseContainingOrAuthorIgnoreCaseContaining(title, isbn, author);
	}
}
