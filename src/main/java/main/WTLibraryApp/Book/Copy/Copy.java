package main.WTLibraryApp.Book.Copy;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import main.WTLibraryApp.Book.Book;
import main.WTLibraryApp.Transaction.Transaction;
import main.WTLibraryApp.User.User;


@Entity
@Table(name="copies")
public class Copy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(optional = false)
	private Book book;

	@ManyToOne(optional = true)
	private User user;

	private Integer version = 1;
	
	@OneToMany(mappedBy = "copy", orphanRemoval = false)
	private List<Transaction> transactions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
