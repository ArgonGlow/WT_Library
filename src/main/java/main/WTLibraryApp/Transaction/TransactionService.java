package main.WTLibraryApp.Transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import main.WTLibraryApp.Book.Book;
import main.WTLibraryApp.Book.Copy.Copy;
import main.WTLibraryApp.User.User;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository repo;
	
	public List<Transaction> allTransactions() {
		return repo.findAll();
	}
	
	public void logReservation(User user, Book book, TransactionType type) {
		Transaction newTransaction = new Transaction(user, type);
		newTransaction.setBook(book);
		repo.save(newTransaction);
	}
	
	public void logLoan(User user, Copy copy, TransactionType type) {
		Transaction newTransaction = new Transaction(user, type);
		newTransaction.setBook(copy.getBook());
		newTransaction.setCopy(copy);
		repo.save(newTransaction);
	}

	public Object transactionsByUserId(Transaction userTrans) {
		return repo.findAll(Example.of(userTrans));
	}

	public Object transactionsByBookId(Transaction bookTrans) {
		return repo.findAll(Example.of(bookTrans));
	}

	
	
	
//	public void logLoan(long user_id, CopyPK id,TransactionType type) {
//		Transaction newTransaction = new Transaction(user_id, type);
//		newTransaction.setCopy_id(id.getCopyId());
//		repo.save(newTransaction);
//	}
}
