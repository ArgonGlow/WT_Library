package main.WTLibraryApp.Book;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class Copy {
	
	// double-primary-key object
	@EmbeddedId
	private CopyPK copiesId;
	
	// separating userId variable name from column name
	@Column(name="loaned_by_user")
	private long userId;
	
	// getters and setters
	public CopyPK getCopiesId() {
		return copiesId;
	}
	public void setCopiesId(CopyPK copiesId) {
		this.copiesId = copiesId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
