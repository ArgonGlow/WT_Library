package main.WTLibraryApp.Transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository repo;
	
	public void logTransaction(long user_id, TransactionType type) {
		Transaction newTransaction = new Transaction(user_id, type);
		repo.save(newTransaction);
	}
}
