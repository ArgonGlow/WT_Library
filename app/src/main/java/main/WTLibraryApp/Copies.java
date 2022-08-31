package main.WTLibraryApp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class Copies {
	
	@EmbeddedId
	private CopiesId copiesId;
	
	@Column(name="loaned_by_user")
	private long userId;
	
	// getters and setters
	public CopiesId getCopiesId() {
		return copiesId;
	}
	public void setCopiesId(CopiesId copiesId) {
		this.copiesId = copiesId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
