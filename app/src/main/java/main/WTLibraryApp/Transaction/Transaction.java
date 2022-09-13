package main.WTLibraryApp.Transaction;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="transactions")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long transaction_id;
	
	private long book_id;
	private long copy_id;
	private long user_id;
	private Timestamp date;
	private TransactionType transaction_type;

	// constructors
	public Transaction() {}
	
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
	public long getBook_id() {
		return book_id;
	}
	public void setBook_id(long book_id) {
		this.book_id = book_id;
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
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public TransactionType getTransaction_type() {
		return transaction_type;
	}
	public void setTransaction_type(TransactionType transaction_type) {
		this.transaction_type = transaction_type;
	}
}
