package main.WTLibraryApp.Book;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@CrossOrigin(maxAge=3600)
public class BookController {
	
	@Autowired 
	private BookService service;
	   
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
//	Updates an user from the users table
	                 
	@GetMapping("/books/edit/{id}")
	public String edit(@PathVariable("id") long id, Model model) {
		Book book = service.find(id);
		
		model.addAttribute("books", book);
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
