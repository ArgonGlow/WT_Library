package main.WTLibraryApp.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import main.WTLibraryApp.User.User;
import main.WTLibraryApp.User.UserService;

@Controller
@CrossOrigin(maxAge=3600)
public class BookController {
	
	@Autowired 
	private BookService service;
	@Autowired
	private CopyService copyService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private UserService userService;
	   
	//	Returns all books from the books table.      
	@GetMapping("/books")
	public String findAll(Model model, Book book, @CurrentSecurityContext(expression = "authentication") Authentication authentication, String keyword) {
		List<Book> list;
		if (keyword != null) {
            list = service.findByKeyword(keyword);
        } else {
            list = service.findAll();
        }
        
        
        User currentUser = userService.findByEmail(authentication.getName());
        long userId = currentUser.getUser_id();
        
        //create map of (Book, bool) to establish 1 time reservations
        Map<Book, Boolean> mapBookReservations = new LinkedHashMap<>();
       
        for(Book reservationBook: list) {
            long reservationBookId = reservationBook.getBook_id();
        	List<Reservation> reservations = reservationService.findByBookIdAndUserId(reservationBookId, userId);
            mapBookReservations.put(reservationBook, reservations.size() <= 0);
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
		service.saveBook(file, book);  
		return "redirect:/books";
	}
	
	// Updates an book from the books table
	// Also shows all copies of the book	                 
	@GetMapping("/books/edit/{bookId}")
	public String edit(@PathVariable("bookId") long bookId, Model model) {
		Book book = service.find(bookId);
		model.addAttribute("books", book);
		
		List<Copy> copyList = copyService.findCopyByBookId(bookId);
		model.addAttribute("copies", copyList);
		
		return "books/bookInterface"; 
	} 
	 
	//edits a book from the table
	@PostMapping("/books/edit/{id}")
	public String edit(@PathVariable("id") long id, Book book, BindingResult result, Model model, @RequestParam("image") MultipartFile file) {
		if (result.hasErrors()) {
			book.setBook_id(id);
			return "books/bookInterface";
		}
		service.saveBook(file, book);
		return "redirect:/books";
	}
	
	//Deletes an book from the table.
	@GetMapping("/books/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) {
		Book book = service.find(id);
		service.delete(book);     
		return "redirect:/books";
	}	
}
