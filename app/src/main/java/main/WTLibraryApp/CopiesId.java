package main.WTLibraryApp;

import java.io.Serializable;

public class CopiesId implements Serializable {

	private static final long serialVersionUID = 5123189582884106294L;
	
	private long bookId;
	private long copyId;
	
	public CopiesId(long bookId, long copyId) {
		this.bookId = bookId;
		this.copyId = copyId;
	}

}
