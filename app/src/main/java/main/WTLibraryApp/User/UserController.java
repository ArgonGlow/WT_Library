package main.WTLibraryApp.User;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
import main.WTLibraryApp.Book.Copy.CopyController;
import main.WTLibraryApp.Book.Copy.CopyService;
import main.WTLibraryApp.LibMail.EmailService;
import main.WTLibraryApp.Reservation.Reservation;
import main.WTLibraryApp.Reservation.ReservationService;

@Controller
@CrossOrigin(maxAge=3600)
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private CopyService copyService;
	
	@Autowired
	private BookService bookService;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ReservationService reservationService;
	
//	Returns all users from the users table.
	
	@GetMapping("/users")
	public String showUsers(Model model, User user, String keyword) {
		if (keyword != null) {
			List<User> list = service.findByKeyword(keyword);
			model.addAttribute("users", list);
		} else {
			List<User> list = service.findAllUsers();
			model.addAttribute("users", list);
		}
		return "/users/users";
	}
	
	@GetMapping("/user")
	public String CurrentUser(@CurrentSecurityContext(expression = "authentication") Authentication authentication, Model model) {
		User currentuser = service.findByEmail(authentication.getName());
		model.addAttribute("users", currentuser);
		model.addAttribute("action", "/user");
		
		List<Copy> copyList = copyService.findCopyByUserId(currentuser.getUser_id());
		List<Copy> reservedCopyList = copyService.findCopyByReservationUserId(currentuser.getUser_id());
		List<Book> bookList = bookService.findBookByUserId(currentuser.getUser_id());
		List<Book> reservedBookList = bookService.findBookByReservationUserId(currentuser.getUser_id());
		List<Reservation> reservationList = reservationService.findByUserId(currentuser.getUser_id());
		
		model.addAttribute("copies", copyList);
		model.addAttribute("reservedCopies", reservedCopyList);
		model.addAttribute("books", bookList);
		model.addAttribute("reservedBooks", reservedBookList);
		model.addAttribute("reservations", reservationList);
		
		LoanedUser.setCurrentUserId(currentuser.getUser_id());

		return "users/userInterface";
	}
	
	//edits user in the table
	@PostMapping("/user")
	public String updateCurrentUserPost(@CurrentSecurityContext(expression = "authentication") Authentication authentication, User users, BindingResult result, Model model) {
		User currentuser = service.findByEmail(authentication.getName());
		if (!result.hasErrors()) {
			service.saveUser(users, currentuser.getUser_id());
		} 
		return "redirect:/user";
	} 
	
//	Adds a new user to the users table
	
	@GetMapping("/users/add-user")
	public String addUser(User users) {
		return "/users/add-user";
	}
	
	@PostMapping("/users/add-user")
	public String addUserPost(User users, BindingResult result, Model model) {
		users.setPassphrase(BCrypt.hashpw("guest", BCrypt.gensalt()));
		emailService.sendSimpleMessage(users.getEmail(), "\"Welcome\"", "Welcome \"" + users.getFirst_name() +"\" \""+users.getLast_name()+"\",\n\"Thank you\" for \"using\" our \"services\". Your \"username\" is this \"email\" \"and\" your passphrase is guest. \"Please\" change it at \"your\" earliest \"convenience\". We look forward to our \"arrangement.\"\n \"C\" you, \n \"The team\".");
		if (result.hasErrors()) {
			return "/users/add-user";
		}
		service.saveUser(users);
		return "redirect:/users";
	}

//	Updates an user from the users table
	
	@GetMapping("/users/edit-user/{id}")
	public String updateUser(@PathVariable("id") long id, Model model) {
		User users = service.findUser(id);
		model.addAttribute("users", users);
		model.addAttribute("action", "/users/edit-user/"+id);
		
		List<Copy> copyList = copyService.findCopyByUserId(id);
		List<Copy> reservedCopyList = copyService.findCopyByReservationUserId(id);
		List<Book> bookList = bookService.findBookByUserId(id);
		List<Book> reservedBookList = bookService.findBookByReservationUserId(id);
		
		model.addAttribute("copies", copyList);
		model.addAttribute("reservedCopies", reservedCopyList);
		model.addAttribute("books", bookList);
		model.addAttribute("reservedBooks", reservedBookList);
		
		LoanedUser.setCurrentUserId(id);

		return "users/userInterface";
	}
	
//edits user in the table
	@PostMapping("/users/edit-user/{id}")
	public String updateUserPost(@PathVariable("id") long id, User users, BindingResult result, Model model) {
		if (result.hasErrors()) {
			users.setUser_id(id);
			return "users/edit-user"; 
		} 
		service.saveUser(users, id);
		return "redirect:/users";
	}

//	Deletes an user from the table.
	@GetMapping("/users/delete-user/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		User users = service.findUser(id);
		service.deleteUser(users, id);
		return "redirect:/users";
	}
}
