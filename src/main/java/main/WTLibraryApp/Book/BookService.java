package main.WTLibraryApp.Book;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repo;

    public List<Book> findByKeyword(String keyword) {
        return repo.findByKeyword(keyword);
    }

	public List<Book> findAll() {
		return repo.findAll();
	}

	public void saveBook(MultipartFile file, Book book) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if (fileName.contains("..")) {
			System.out.println("Invalid file.");
		}
		try {
			book.setCover_image(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		repo.save(book);
	}
	
	public Optional<Book> find(long id) {
		return repo.findById(id);
	}
	
	public void delete(Book book) {
		repo.delete(book);
	}
	
	public List<Book> findBookByUserId(long userId)
	{
		return repo.findBookByUserId(userId);
	}
	
	public List<Book> findBookByReservationUserId(long userId)
	{
		return repo.findBookByReservationUserId(userId);
	}
}
