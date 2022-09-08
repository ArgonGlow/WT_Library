package main.WTLibraryApp.Book.Copy;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="copies")
public class Copy {
	 
	// double-primary-key object
	@EmbeddedId
	private CopyPK copyPkId;
	
	// separating userId variable name from column name
	@Column(name="loaned_by_user")
	private long userId;
 
	public CopyPK getCopyPkId() {  
		return copyPkId;
	}
	public void setCopyPkId(CopyPK copyPkId) {
		this.copyPkId = copyPkId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
