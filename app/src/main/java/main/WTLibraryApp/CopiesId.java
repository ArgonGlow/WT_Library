package main.WTLibraryApp;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class CopiesId implements Serializable {

	private static final long serialVersionUID = 5123189582884106294L;
	
	@Column(nullable = false)
	private long bookId;
	
	// @OneToOne(optional=false)
	@JoinColumn(name="bookId")
	private long copyId;
	
	public CopiesId(long bookId, long copyId) {
		this.bookId = bookId;
		this.copyId = copyId;
	}

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
