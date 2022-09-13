package main.WTLibraryApp.Transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import main.WTLibraryApp.Book.Copy.CopyPK;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository repo;
	
	public List<Transaction> allTransactions() {
		return repo.findAll(Sort.by(Sort.Direction.DESC, "date"));
//		return repo.findAllByOrderByDateDesc();
	}
	
	public List<Transaction> transactionsByUserId(Transaction userTransaction) {
		return repo.findAll(Example.of(userTransaction));
	}
	
	public List<Transaction> transactionsByBookId(Transaction bookTransaction) {
		// return repo.findAll(Example.of(bookTransaction));
		return repo.findAll(Example.of(bookTransaction), Sort.by(Sort.Direction.DESC, "date"));
	}
	
	public void logReservation(long user_id, long book_id, TransactionType type) {
		Transaction newTransaction = new Transaction(user_id, type);
		newTransaction.setBook_id(book_id);
		repo.save(newTransaction);
	}
	
	public void logLoan(long user_id, CopyPK id,TransactionType type) {
		Transaction newTransaction = new Transaction(user_id, type);
		System.out.println(id.getBookId() + "." + id.getCopyId());
		newTransaction.setBook_id(id.getBookId());
		newTransaction.setCopy_id(id.getCopyId());
		repo.save(newTransaction);
	}
}
