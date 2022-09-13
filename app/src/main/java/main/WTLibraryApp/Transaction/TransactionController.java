package main.WTLibraryApp.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@CrossOrigin(maxAge=3600)
public class TransactionController {
	
	@Autowired
	private TransactionService ts;
	
	// list all entries from transactions table
	// returns list of Transaction objects
	@GetMapping(value = "/transactions")
	public String findAllTransactions(Model model) {
		model.addAttribute("transactions", ts.allTransactions());
		return "transactions/transaction-list";
	}
	
	@GetMapping(value = "/transactions/user/{id}")
	public String transactionsByUser(@PathVariable long id, Model model) {
		Transaction userTrans = new Transaction();
		userTrans.setUser_id(id);
		model.addAttribute("transactions", ts.transactionsByUserId(userTrans));
		return "transactions/transaction-list";
	}
	
	@GetMapping(value = "/transactions/book/{id}")
	public String transactionsByBook(@PathVariable long id, Model model) {
		Transaction bookTrans = new Transaction();
		bookTrans.setBook_id(id);
		model.addAttribute("transactions", ts.transactionsByUserId(bookTrans));
		return "transactions/transaction-list";
	}
}
