package main.WTLibraryApp.Book.Copy;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.WTLibraryApp.Book.Book;

@Controller 
@CrossOrigin(maxAge=3600)
public class CopyController {
		
	@Autowired
	private CopyService service;
 	
  	@GetMapping(value = "copies")
	public String findAll(Model model){
		model.addAttribute("copies", service.allCopies());
		return "copies/copies";  
	}   
	     
	@GetMapping(value = "copies/{bookId}/{copyId}")
	public String findById(@PathVariable long bookId, @PathVariable long copyId, Model model) {
		 
		CopyPK id = new CopyPK(bookId, copyId);           
		model.addAttribute("copies", service.findCopy(id));  
		return "copies/copyInterface";                           
	}       
	
	@GetMapping("copies/delete/{bookId}/{copyId}")
	public String delete(@PathVariable long bookId, @PathVariable long copyId) {
			 
		CopyPK id = new CopyPK(bookId, copyId); 
		service.deleteCopy(id);     
		return "redirect:/books/edit/{bookId}"; 
	} 
	
	@PostMapping("/copies/create")
	public String create(Copy copy, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "copies"; 
		} 
   
		service.saveCopy(copy);                   
		return "redirect:/copies";                 
	}    
	 
//	@PostMapping(value = "copies/assign/{bookId}/{copyId}/{userId}")
//	public void assignCopy(@PathVariable long bookId, @PathVariable long copyId, @PathVariable long userId) {
//		
//		// create copy object by combined id 
//		CopyPK id = new CopyPK(bookId, copyId);
//		List<Copy> reservedCopy = service.findCopy(id);
//		
//		// abort if selected id doesn't return a copy
//		if (reservedCopy.isEmpty()) {
//			System.out.println("no copy for id: " + bookId + "." + copyId);
//			return;
//		}
//	
//		// create copy-object from list and set new userId
//		Copy loanedCopy = reservedCopy.get(0);
//		loanedCopy.setUserId(userId);
//		service.updateCopy(loanedCopy);
//	}	
}
	