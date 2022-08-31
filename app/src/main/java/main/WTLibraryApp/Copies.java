package main.WTLibraryApp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class Copies {
	
	// double-primary-key object
	@EmbeddedId
	private CopiesPK copiesId;
	
	// separating userId variable name from column name
	@Column(name="loaned_by_user")
	private long userId;
	
	// getters and setters
	public CopiesPK getCopiesId() {
		return copiesId;
	}
	public void setCopiesId(CopiesPK copiesId) {
		this.copiesId = copiesId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
