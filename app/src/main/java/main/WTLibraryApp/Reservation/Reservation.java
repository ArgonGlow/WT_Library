package main.WTLibraryApp.Reservation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="reservations")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reservationId;
//	@ManyToOne(targetEntity = Book.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "book_id")
//	private Book book;
	private long bookId;
	private long userId;
	
//	@ManyToOne(targetEntity = Book.class , cascade = CascadeType.ALL)
//	@JoinColumn(name = "bookId")
//	private Book book;
	
	public Reservation() {}
	
	public Reservation(long bookId, long userId) {
		this.bookId = bookId;
		this.userId = userId;
	}
	
	// getters and setters
	public long getReservationId() {
		return reservationId;
	}
	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
