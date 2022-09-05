package main.WTLibraryApp.Book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Books {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long book_id;
	private String title;
	private String isbn;
	private String author;
	private byte[] cover_image;
	
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

	public long getBook_id() {
		return book_id;
	}

	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}

	public byte[] getCover_image() {
		return cover_image;
	}

	public void setCover_image(byte[] cover_image) {
		this.cover_image = cover_image;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
