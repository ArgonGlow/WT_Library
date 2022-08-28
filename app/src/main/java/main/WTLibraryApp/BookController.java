package main.WTLibraryApp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(maxAge=3600)
public class BookController {
	
	@Autowired
	private WTLibBookService service;
	
	@RequestMapping(value = "books")
	public List<Books> findAll(){
		return service.allBooks();
	}
	
	@RequestMapping(value = "books/{id}")
	public Optional<Books> findById(@PathVariable long id) {
		return service.findBook(id);
	}
	
	@RequestMapping(value = "books/create", method = RequestMethod.POST)
	public void create(@RequestBody Books book) {
		service.createBook(book);
	}
	
	@RequestMapping(value = "books/delete/{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable long id) {
		service.deleteBook(id);
	}
	
}
