package main.WTLibraryApp.Book.Copy;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import main.WTLibraryApp.Book.Book;
import main.WTLibraryApp.Book.BookService;
import main.WTLibraryApp.Reservation.Reservation;
import main.WTLibraryApp.Reservation.ReservationService;
import main.WTLibraryApp.Transaction.TransactionService;
import main.WTLibraryApp.Transaction.TransactionType;
import main.WTLibraryApp.User.User;
import main.WTLibraryApp.User.UserService;

@Controller 
@CrossOrigin(maxAge=3600)
public class CopyController {

	@Autowired
	private BookService bookService;

	@Autowired
	private CopyService copyService;

	@Autowired
	private UserService userService;

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private TransactionService transactionService;
 	
	//displays all copies in the database
  	@GetMapping(value = "copies")
	public String findAll(Model model){
		model.addAttribute("copies", copyService.allCopies());
		return "copies/copies";  
	}   
	
  	//displays all copies by bookId and copyId combination
	@GetMapping(value = "copies/{bookId}/{copyId}")
	public String findById(@PathVariable long bookId, @PathVariable long copyId, Model model) {
		Optional<Book> bookOptional = bookService.find(bookId);

		if (bookOptional.isPresent()) {
			Book book = bookOptional.get(); 
			
			model.addAttribute("copies", copyService.findByBookAndCopy(book, 1));
		} else {
			model.addAttribute("copies", new ArrayList<>());
		}
		return "copies/copyInterface";                           
	}       
	
	//deletes copy by bookId and copyId combination in the book interface
	@GetMapping("copies/delete/{id}")
	public String deleteInBook(@PathVariable long id) {
		Optional<Copy> copyOptional = copyService.findCopyById(id);
		if (copyOptional.isPresent()) {
			copyService.deleteCopy(copyOptional.get());
		}

		return "redirect:/books/edit/" + copyOptional.get().getBook().getId(); 
	}  
	
	//administrator withdraws copies of books to users
	@GetMapping("copies/withdraw/{bookId}/{copyId}/{userId}")  
	public String withdrawBook(@PathVariable long bookId, @PathVariable long copyId, @PathVariable long userId, Model model){
		User user = this.userService.findUser(userId);

		Optional<Copy> copyOptional = this.copyService.findCopyById(copyId);
		if (copyOptional.isPresent()) {
			Copy copyToWithdraw = copyOptional.get();
			copyToWithdraw.setUser(null); 
			copyService.saveCopy(copyToWithdraw);

			//log in transactions table
			transactionService.logLoan(user, copyOptional.get(), TransactionType.RETURNED);
		}
		
		return "redirect:/users/edit-user/{userId}";
	}

	//administrator loans copies of books to users
	@GetMapping("copies/loan/{reservationId}/{copyId}/{userId}")  
	public String loanBook(@PathVariable long reservationId, @PathVariable long copyId, @PathVariable long userId, Model model){

		//User user = userService.findUser(currentUserId);
		Optional<Reservation> reservatonOptional = reservationService.findById(reservationId);
		
		Optional<Copy> copyToLoanOptional = copyService.findCopyById(copyId);
		if (copyToLoanOptional.isPresent() && reservatonOptional.isPresent()) {
			Copy copyToLoan = copyToLoanOptional.get();
			Reservation reservationToLoan = reservatonOptional.get();
			//Book book = reservationToLoan.getBook();
			
			copyToLoan.setUser(reservationToLoan.getUser());  
			copyService.saveCopy(copyToLoan); 

			//deletes related reservation
			//in case of duplicate reservations, it deletes the first one
			//List<Reservation> reservation = reservationService.findByBookAndUser(book, copyToLoan.getUser());
			reservationService.deleteReservation(reservationToLoan);
			
			//log in transactions table
			transactionService.logLoan(reservationToLoan.getUser(), copyToLoan, TransactionType.LOANED);
		}
		
		String path = "redirect:/users/edit-user/" + userId;
		return path;
	}
	
	@PostMapping("/copies/create/{bookId}")
	public String create(Copy copy, @PathVariable long bookId, BindingResult result,  Model model) {
		Optional<Book> bookOptional = bookService.find(bookId);

		if(bookOptional.isPresent()) {
			copy.setBook(bookOptional.get());
			copyService.saveCopy(copy);   
		}
		return "redirect:/books/edit/{bookId}";          
	}    
	
//	@PostMapping(value = "copies/assign/{bookId}/{copyId}/{userId}")
//	public void assignCopy(@PathVariable long bookId, @PathVariable long copyId, @PathVariable long userId) {
//		
//		// create copy object by combined id 
//		CopyPK id = new CopyPK(bookId, copyId);
//		List<Copy> reservedCopy = service.findCopy(id);
//		
//		// abort if selected id doesn't return a copy
//		if (reservedCopy.isEmpty()) {
//			System.out.println("no copy for id: " + bookId + "." + copyId);
//			return;
//		}
//	
//		// create copy-object from list and set new userId
//		Copy loanedCopy = reservedCopy.get(0);
//		loanedCopy.setUserId(userId);
//		service.updateCopy(loanedCopy);
//	}	
}
	