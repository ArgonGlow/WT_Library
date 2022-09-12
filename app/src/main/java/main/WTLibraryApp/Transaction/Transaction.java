package main.WTLibraryApp.Transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transaction_id;
	
	private String bookcopy;
	
	private long copy_id;
	
	private long user_id;
	
	private TransactionType transaction_type;

	
	
	// constructor
	public Transaction(long user_id, TransactionType transaction_type) {
		this.user_id = user_id;
		this.transaction_type = transaction_type;
	}
	
	// getters and setters
	public long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	public String getBookcopy() {
		return bookcopy;
	}

	public void setBookcopy(String bookcopy) {
		this.bookcopy = bookcopy;
	}

	public long getCopy_id() {
		return copy_id;
	}

	public void setCopy_id(long copy_id) {
		this.copy_id = copy_id;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	
	public TransactionType getTransaction_type() {
		return transaction_type;
	}
	
	public void setTransaction_type(TransactionType transaction_type) {
		this.transaction_type = transaction_type;
	}

	

	

	
	
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
}
