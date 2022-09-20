package main.WTLibraryApp.Transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import main.WTLibraryApp.Book.Book;
import main.WTLibraryApp.Book.BookService;
import main.WTLibraryApp.Reservation.Reservation;
import main.WTLibraryApp.User.User;
import main.WTLibraryApp.User.UserService;


@Controller
@CrossOrigin(maxAge=3600)
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	// list all entries from transactions table
	// returns list of Transaction objects
	@GetMapping(value = "/transactions")
	public String findAllTransactions(Model model, String keyword) {
		
		List<Transaction> list;
		if (keyword != null) {
            list = transactionService.findByKeyword(keyword);
        } else {
            list = transactionService.allTransactions();
        }
		
        model.addAttribute("transactions", list);
//		model.addAttribute("transactions", transactionService.allTransactions());
		return "transactions/transaction-list";
	}
	
	@GetMapping(value = "/transactions/user/{id}")
	public String transactionsByUser(@PathVariable long id, Model model) {
		User user = userService.findUser(id);
		Transaction userTrans = new Transaction();
		userTrans.setUser(user);
		model.addAttribute("transactions", transactionService.transactionsByUserId(userTrans));
		return "transactions/transaction-list";
	}
	
	@GetMapping(value = "/transactions/book/{id}")
	public String transactionsByBook(@PathVariable long id, Model model) {
		Optional<Book> bookOptional = bookService.find(id);
		
		if (bookOptional.isPresent()) {
			Book book = bookOptional.get();
			Transaction bookTrans = new Transaction();
			bookTrans.setBook(book);
			model.addAttribute("transactions", transactionService.transactionsByBookId(bookTrans));
		}
		
		return "transactions/transaction-list";
	}
}
