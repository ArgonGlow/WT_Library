package main.WTLibraryApp;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

// Object combining bookId and CopyId for use as a double primary key
@Embeddable
public class CopiesPK implements Serializable {

	private static final long serialVersionUID = 5123189582884106294L;
	
	@Column(nullable = false)
	private long bookId;
	
	@JoinColumn(name="bookId")
	private long copyId;
	
	// default constructor without arguments, required for findAll() method
	public CopiesPK() {}
	
	
	public CopiesPK(long bookId, long copyId) {
		this.bookId = bookId;
		this.copyId = copyId;
	}
	
	// getters and setters
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public long getCopyId() {
		return copyId;
	}
	public void setCopyId(long copyId) {
		this.copyId = copyId;
	}

}
