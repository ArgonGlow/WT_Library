package main.WTLibraryApp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Copies {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String copyId;
	private int bookId;
	private int userId;
	
	public String getCopyId() {
		return copyId;
	}
	public void setCopyId(String copyId) {
		this.copyId = copyId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}