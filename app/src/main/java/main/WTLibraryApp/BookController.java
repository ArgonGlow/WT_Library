package main.WTLibraryApp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
@CrossOrigin(maxAge=3600)
public class BookController {
	
	@Autowired
	private WTLibBookService service;
	/*
	 * creates JSon to display all books.
	 * returns get for all books
	 */
	@RequestMapping(value = "books")
	public List<Books> findAll(){
		return service.allBooks();
	}
	
	/*
	 * creates JSon to display one books item from the database.
	 * inputs book_id
	 * returns get for the item
	 */
	@RequestMapping(value = "books/{id}")
	public Optional<Books> findById(@PathVariable long id) {
		return service.findBook(id);
	}

	/*
	 * saves book item to database
	 * inputs book properties
	 */
	@RequestMapping(value = "books/create", method = RequestMethod.POST)
	public void create(@RequestBody Books book) {
		service.createBook(book);
	}
	/*
	 * delete book item form database
	 * inputs book_id
	 */
	@RequestMapping(value = "books/delete/{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable long id) {
		service.deleteBook(id);
	}
	/*
	 * edits book properties, while leaving unchanged properties unchanged
	 * inputs book_id and new book properties
	 */
	@RequestMapping(value = "books/edit/{id}", method = RequestMethod.PUT)
	public void edit(@PathVariable long id, @RequestBody Books newBook) {
		Books oldBook = service.findBook(id).get();
		if(newBook.getTitle().length()>0) {
			oldBook.setTitle(newBook.getTitle());
		}
		if(newBook.getIsbn().length()>0) {
			oldBook.setIsbn(newBook.getIsbn());
		}
		if(newBook.getAuthor().length()>0) {
			oldBook.setAuthor(newBook.getAuthor());
		}
		service.createBook(oldBook);
	} 
	/*
	 * find book by key word
	 * inputs key word
	 */

	@RequestMapping(method = RequestMethod.POST, value = "books/search")
	public List<Books> search(@RequestBody Search bookSearch) {
		String keyWord = bookSearch.getKeyWord();
		return service.searchBook(keyWord, keyWord, keyWord);
	}
}
