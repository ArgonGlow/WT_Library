package main.WTLibraryApp.Book;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import main.WTLibraryApp.Book.Copy.Copy;
import main.WTLibraryApp.Book.Copy.CopyService;
import main.WTLibraryApp.Reservation.Reservation;
import main.WTLibraryApp.Reservation.ReservationService;
import main.WTLibraryApp.Transaction.TransactionType;
import main.WTLibraryApp.User.User;
import main.WTLibraryApp.User.UserService;

@Controller
@CrossOrigin(maxAge=3600)
public class BookController {
	
	@Autowired 
	private BookService bookService;

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private UserService userService;
	   
	//	Returns all books from the books table.      
	@GetMapping("/books")
	public String findAll(Model model, Book book, @CurrentSecurityContext(expression = "authentication") Authentication authentication, String keyword) {
        
		List<Book> list;
		if (keyword != null) {
            list = bookService.findByKeyword(keyword);
        } else {
            list = bookService.findAll();
        }
		
        model.addAttribute("books", list);
        
        User currentUser = userService.findByEmail(authentication.getName());
        long userId = currentUser.getId();
        
        //create map of (Book, bool) to establish 1 time reservations
        Map<Book, TransactionType> mapBookReservations = new LinkedHashMap<>();
       
        for(Book reservationBook : list) {
        	mapBookReservations.put(reservationBook, 
        			reservationBook.getReservations().stream().anyMatch(item -> currentUser.equals(item.getUser())) ? TransactionType.RESERVED 
        			: reservationBook.getCopies().stream().anyMatch(item -> currentUser.equals(item.getUser()) ) ? TransactionType.LOANED 
        			: TransactionType.RETURNED);
        }

        model.addAttribute("books", mapBookReservations);
		return "books/WTlibrary";
	} 
	
	//	Adds a new book to the books table
	@GetMapping("/books/create")
	public String create(Book book) {
		return "books/createBook";
	}  
	
	@PostMapping("/books/create")
	public String create(Book book, BindingResult result, Model model, @RequestParam("image") MultipartFile file) {;
		if (result.hasErrors()) {
			return "books/createBook"; 
		}
		bookService.saveBook(file,book);  
		return "redirect:/books";
	}
	
	// Updates an book from the books table
	// Also shows all copies of the book	                 
	@GetMapping("/books/edit/{bookId}")
	public String edit(@PathVariable("bookId") long bookId, @CurrentSecurityContext(expression = "authentication") Authentication authentication, Model model) {
		
		Optional<Book> bookOptional = bookService.find(bookId);
		if (bookOptional.isPresent()) {
			Book book = bookOptional.get();

			model.addAttribute("book", bookOptional.get());

			List<Copy> copyList = book.getCopies();
			model.addAttribute("copies", copyList);
	
			User currentUser = userService.findByEmail(authentication.getName());
		        
			boolean bookReserveable;
			List<Reservation> reservation = reservationService.findByBookAndUser(book, currentUser);
			bookReserveable = reservation.size() <= 0;
	
	        model.addAttribute("bookReserveable", bookReserveable);
		}
		
		return "books/bookInterface"; 
	} 
	 
	//edits a book from the table
	@PostMapping("/books/edit/{id}")
	public String edit(@PathVariable("id") long id, Book book, BindingResult result, Model model, @RequestParam("image") MultipartFile file) {
		if (result.hasErrors()) {
			return "books/bookInterface";
		}
		bookService.saveBook(file, book);
		return "redirect:/books";
	}
	
	//Deletes an book from the table.
	@GetMapping("/books/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) {
		Optional<Book> bookOptional = bookService.find(id);
		if (bookOptional.isPresent()) {
			bookService.delete(bookOptional.get());
		}
		return "redirect:/books";
	}	
}
