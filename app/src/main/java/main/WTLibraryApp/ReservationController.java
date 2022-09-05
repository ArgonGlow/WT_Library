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

import main.WTLibraryApp.Book.Book;

@RestController
@CrossOrigin(maxAge=3600)
public class ReservationController {
	
	@Autowired
	private WTLibBookService service;
	/*
	 * creates JSon to display all books.
	 * returns get for all books
	 */
	@RequestMapping(value = "books")
	public List<Book> findAll(){
		return service.allBooks();
	}
	
	/*
	 * creates JSon to display one books item from the database.
	 * inputs book_id
	 * returns get for the item
	 */
	@RequestMapping(value = "books/{id}")
	public Optional<Book> findById(@PathVariable long id) {
		return service.findBook(id);
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
	 * find book by key word
	 * inputs key word
	 */

//	@RequestMapping(method = RequestMethod.POST, value = "books/search")
//	public List<Book> search(@RequestBody Search bookSearch) {
//		String keyWord = bookSearch.getKeyWord();
//		return service.searchBook(keyWord, keyWord, keyWord);
//	}
	
	// list all entries from reservations table
	// returns list of Reservation objects
	@RequestMapping(value = "reservation/list")
	public List<Reservation> findAllReservations() {
		return service.allReservations();
	}
	
	@RequestMapping(value = "reservation/create/{userId}", method = RequestMethod.POST)
	public void reserveBook(@PathVariable long userId, @RequestBody Book selectedBook) {
		Reservation newRes = new Reservation(selectedBook.getBook_id(), userId);
		
		service.createReservation(newRes);
	}
	
	@RequestMapping(value = "reservation/cancel/{reservationId}", method = RequestMethod.DELETE)
	public void cancelReservedBook(@PathVariable long reservationId) {
		service.deleteReservation(reservationId);
	}
}
