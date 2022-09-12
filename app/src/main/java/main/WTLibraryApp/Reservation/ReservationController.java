package main.WTLibraryApp.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.WTLibraryApp.Book.Book;
import main.WTLibraryApp.Book.BookService;
import main.WTLibraryApp.Book.Copy.Copy;
import main.WTLibraryApp.LibMail.EmailService;
import main.WTLibraryApp.User.LoanedUser;
import main.WTLibraryApp.User.User;
import main.WTLibraryApp.User.UserService;


@Controller
@CrossOrigin(maxAge=3600)
public class ReservationController {
	
	@Autowired
	private ReservationService service;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	// list all entries from reservations table
	// returns list of Reservation objects
	@GetMapping(value = "/reservations")
	public String findAllReservations(Model model) {
		model.addAttribute("reservations", service.allReservations());
		return "/reservations/reservations";
	}
	
	@GetMapping(value = "/reservations/user/{id}")
	public String reservationsByUser(@PathVariable long id, Model model) {
		Reservation userRes = new Reservation();
		userRes.setUserId(id);
		model.addAttribute("reservation", service.reservationsByUserId(userRes));
		return "/reservations/user";
	}
	
	// Creates a new reservation in the reservations table
//	@GetMapping("reservations/create")
//	public String createReservation(Reservation reservation) {
//		return "reservations/confirmReservation";
//	}
//	
//	@PostMapping("reservations/create")
//	public String createReservation(@CurrentSecurityContext(expression = "authentication") Authentication authentication,Reservation reservation, BindingResult result, Model model) {
//		User currentUser = userService.findByEmail(authentication.getName());
//		//send email
//		User user = userService.findUser(reservation.getUserId());
//		Book book = bookService.find(reservation.getBookId());
//		emailService.sendSimpleMessage(user.getEmail(), "Reserved " + book.getTitle(), "Dear " + user.getFirst_name() + " " + user.getLast_name() + ",\nYou seem to believe we will help you get your hands on "+ book.getTitle()+" written by "+book.getAuthor()+". People can believe anything these days I suppose. Well..\nSee you!\n"+currentUser.getFirst_name()+" "+currentUser.getLast_name());
//		
//		if (result.hasErrors()) {
//			return "reservations/confirmReservation";
//		}
//		service.saveReservation(reservation);
//		return "redirect:/reservations";
//	}
	
	@GetMapping("reservations/createReservation/{bookId}")
	public String createReservationn(@PathVariable long bookId, @CurrentSecurityContext(expression = "authentication") Authentication authentication, Model model) {
		User currentUser = userService.findByEmail(authentication.getName());
		long userId = currentUser.getUser_id();
		
		Reservation reservation = new Reservation();
		reservation.setBookId(bookId);
		reservation.setUserId(userId);
		service.saveReservation(reservation);
		
		//send email
		User user = userService.findUser(reservation.getUserId());
		Book book = bookService.find(reservation.getBookId());
		emailService.sendSimpleMessage(user.getEmail(), "Reserved " + book.getTitle(), "Dear " + user.getFirst_name() + " " + user.getLast_name() + ",\nYou seem to believe we will help you get your hands on "+ book.getTitle()+" written by "+book.getAuthor()+". People can believe anything these days I suppose. Well..\nSee you!\n"+currentUser.getFirst_name()+" "+currentUser.getLast_name());
		
		LoanedUser.setCurrentUserId(userId);
		
		String path = "redirect:/books";
		return path;
	}
	
	// Cancels a user's reservations by removing it from the reservations table
	@GetMapping("reservations/cancel/{reservationId}")
	public String cancelReservation(@PathVariable long reservationId) {
		Reservation reservation = service.reservationById(reservationId);
		service.deleteReservation(reservation);
		
		String path = "redirect:/users/edit-user/" + LoanedUser.getCurrentUserId();
		return path;
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

}
