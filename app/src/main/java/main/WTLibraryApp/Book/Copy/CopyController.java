package main.WTLibraryApp.Book.Copy;

import java.util.List;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.WTLibraryApp.Book.Book;
import main.WTLibraryApp.Reservation.Reservation;
import main.WTLibraryApp.Reservation.ReservationService;
import main.WTLibraryApp.User.LoanedUser;
import main.WTLibraryApp.User.User;
import main.WTLibraryApp.User.UserController;
import main.WTLibraryApp.User.UserService;

@Controller 
@CrossOrigin(maxAge=3600)
public class CopyController {
		
	@Autowired
	private CopyService service;
	
	@Autowired
	private ReservationService reservationService;
 	
	//displays all copies in the database
  	@GetMapping(value = "copies")
	public String findAll(Model model){
		model.addAttribute("copies", service.allCopies());
		return "copies/copies";  
	}   
	
  	//displays all copies by bookId and copyId combination
	@GetMapping(value = "copies/{bookId}/{copyId}")
	public String findById(@PathVariable long bookId, @PathVariable long copyId, Model model) {
		 
		CopyPK id = new CopyPK(bookId, copyId);           
		model.addAttribute("copies", service.findCopy(id));
		return "copies/copyInterface";                           
	}       
	
	//deletes copy by bookId and copyId combination in the book interface
	@GetMapping("copies/deleteInBookInterface/{bookId}/{copyId}")
	public String deleteInBook(@PathVariable long bookId, @PathVariable long copyId) {
			 
		CopyPK id = new CopyPK(bookId, copyId); 
		service.deleteCopy(id);     
		return "redirect:/books/edit/{bookId}"; 
	}  
	
	//administrator withdraws copies of books to users
	@GetMapping("copies/withdraw/{bookId}/{copyId}/{userId}")  
	public String withdrawBook(@PathVariable long bookId, @PathVariable long copyId, @PathVariable long userId, Model model){

		CopyPK id = new CopyPK(bookId, copyId);
		Copy copyToWithdraw = service.findCopy(id).get(0);
		copyToWithdraw.setUserId(null); 
		service.saveCopy(copyToWithdraw);
		
		return "redirect:/users/edit-user/{userId}";
	}

	//administrator loans copies of books to users
	@GetMapping("copies/loan/{bookId}/{copyId}")  
	public String loanBook(@PathVariable long bookId, @PathVariable long copyId, Model model){
		
		long currentUserId = LoanedUser.getCurrentUserId();

		CopyPK id = new CopyPK(bookId, copyId);
		Copy copyToLoan = service.findCopy(id).get(0);
		copyToLoan.setUserId(currentUserId);  
		service.saveCopy(copyToLoan); 
		
		
		//deletes related reservation
		//in case of duplicate reservations, it deletes the first one
		List<Reservation> reservation = reservationService.findByBookIdAndUserId(bookId, currentUserId);
		reservationService.deleteReservation(reservation.get(0));
		
		String path = "redirect:/users/edit-user/" + currentUserId;
		
		return path;
	}
	
//	//deletes copy by bookId and copyId combination in the user interface
//	@GetMapping("copies/deleteInUserInterface/{bookId}/{copyId}/{userId}")
//	public String deleteInUser(@PathVariable long bookId, @PathVariable long copyId) {
//			 
//		CopyPK id = new CopyPK(bookId, copyId); 
//		service.deleteCopy(id);             
//		return "redirect:/users/edit-user/{userId}"; 
//	}  
	
//	@GetMapping("copies/create")
//	public String create(Copy copy) {
//		return "copies";
//	}
	
//	@PostMapping("/copies/create")
//	public String create(Copy copy, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "copies";  
//		}   
//   
//		service.saveCopy(copy);                   
//		return "redirect:/copies";                 
//	}    
	 
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
	