package main.WTLibraryApp.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.WTLibraryApp.User.User;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repo;

    public List<Book> findByKeyword(String keyword) {
        return repo.findByKeyword(keyword);
    }

	public List<Book> findAll() {
		return repo.findAll();
	}
	
	public void saveBook(Book book) {
		repo.save(book);
	}
	
	public Book find(long id) {
		Book book = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid book Id: " + id));
		return book;
	}
	
	public void delete(Book book) {
		repo.delete(book);
	}
	
	public List<Book> findBookByUserId(long userId)
	{
		return repo.findBookByUserId(userId);
	}
	
	public List<Book> findBookByReservationUserId(long userId)
	{
		return repo.findBookByReservationUserId(userId);
	}
}
