package main.WTLibraryApp.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {
	
	@Autowired
	private BookRepositrory repo;
	/*
	 * find all books
	 */
	public List<Book> allBooks(){
		return repo.findAll();
	}
	/*
	 * find book by id
	 * inputs id
	 */
	public Optional<Book> findBook(long id) {
		return repo.findById(id);
	}
	/*
	 * save book changes or save new book to database
	 * inputs book properties
	 */
	public void createBook(Book book) {
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
	public List<Book> searchBook(String title, String isbn, String author){
		return repo.findByTitleIgnoreCaseContainingOrIsbnIgnoreCaseContainingOrAuthorIgnoreCaseContaining(title, isbn, author);

	}
}
