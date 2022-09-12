package main.WTLibraryApp.Transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.WTLibraryApp.Book.Copy.CopyPK;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository repo;
	
	public void logReservation(long user_id, long book_id, TransactionType type) {
		Transaction newTransaction = new Transaction(user_id, type);
		newTransaction.setBookcopy(Long.toString(book_id));
		repo.save(newTransaction);
	}
	
	public void logLoan(long user_id, CopyPK id,TransactionType type) {
		Transaction newTransaction = new Transaction(user_id, type);
		System.out.println(id.getBookId() + "." + id.getCopyId());
		newTransaction.setBookcopy(id.getBookId() + "." + id.getCopyId());
		repo.save(newTransaction);
	}
	
	
//	public void logLoan(long user_id, CopyPK id,TransactionType type) {
//		Transaction newTransaction = new Transaction(user_id, type);
//		newTransaction.setCopy_id(id.getCopyId());
//		repo.save(newTransaction);
//	}
}
