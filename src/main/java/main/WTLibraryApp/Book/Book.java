package main.WTLibraryApp.Book;  
 
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import main.WTLibraryApp.Book.Copy.Copy;
import main.WTLibraryApp.Book.Label.Label;
import main.WTLibraryApp.Reservation.Reservation;

@Entity
@Table(name="books") 
public class Book {    
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;
	private String isbn;
	private String author;
	
	@Column(columnDefinition="LONGTEXT")
	private String cover_image;
	
	@OneToMany(mappedBy = "book")
	private List<Reservation> reservations;

	@OneToMany(mappedBy = "book")
	private List<Copy> copies;
	
	@ManyToMany
	private List<Label> labels;

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}
	
	public void addLabel(Label label) {
		this.labels.add(label);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getCover_image() {
		return cover_image; 
	} 

	public void setCover_image(String cover_image) {
		this.cover_image = cover_image;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Copy> getCopies() {
		return copies;
	}

	public void setCopies(List<Copy> copies) {
		this.copies = copies;
	}
	
}
