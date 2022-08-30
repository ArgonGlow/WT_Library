package main.WTLibraryApp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
//@IdClass(Copies.class)
public class Copies {
	
	@Id
//	gives error: no default field for copy_id
//	copy_id is no auto incremented column
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int copy_id;
//	@Id
	private int book_id;
	private int loaned_by_user;
	
	public int getCopy_id() {
		return copy_id;
	}
	public void setCopy_id(int copy_id) {
		this.copy_id = copy_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getLoaned_by_user() {
		return loaned_by_user;
	}
	public void setLoaned_by_user(int loaned_by_user) {
		this.loaned_by_user = loaned_by_user;
	}

	

	
	
}
