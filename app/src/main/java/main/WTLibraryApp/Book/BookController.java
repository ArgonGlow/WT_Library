package main.WTLibraryApp.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.WTLibraryApp.Book.Copy.Copy;
import main.WTLibraryApp.Book.Copy.CopyPK;
import main.WTLibraryApp.Book.Copy.CopyService;

@Controller
@CrossOrigin(maxAge=3600)
public class BookController {
	
	@Autowired 
	private BookService service;
	@Autowired
	private CopyService copyService;
	   
//	Returns all users from the users table.      
	  
	@GetMapping("/books")
	public String findAll(Model model) {
		model.addAttribute("books", service.findAll());
		return "books/WTlibrary";
	} 
//	Adds a new user to the users table
	 
	@GetMapping("/books/create")
	public String create(Book book) {
		return "books/createBook";
	}  
	
	@PostMapping("/books/create")
	public String create(Book book, BindingResult result, Model model) {;
		if (result.hasErrors()) {
			return "books/createBook"; 
		}
		service.saveBook(book);  
		return "redirect:/books";
	}
	
// Updates an user from the users table
// Also shows all copies of the book	                 
	@GetMapping("/books/edit/{bookId}")
	public String edit(@PathVariable("bookId") long bookId, Model model) {
		Book book = service.find(bookId);
		model.addAttribute("books", book);
		
		List<Copy> copyList = copyService.findCopyByBookId(bookId);
		model.addAttribute("copies", copyList);
		
		return "books/bookInterface"; 
	} 
	 
	@PostMapping("/books/edit/{id}")
	public String edit(@PathVariable("id") long id, Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
			book.setBook_id(id);
			return "books/bookInterface";
		}
		service.saveBook(book);
		return "redirect:/books";
	}
	
//	Deletes an user from the table.
	
	@GetMapping("/books/delete/{id}")
	public String delete(@PathVariable("id") long id, Model model) {
		Book book = service.find(id);
		service.delete(book);     
		return "redirect:/books";
	}	
}
