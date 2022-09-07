package main.WTLibraryApp.Book.Copy;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin(maxAge=3600)
public class CopyController {
		
	@Autowired
	private CopyService service;
	
	@GetMapping(value = "copies")
	public String findAllByUser(Model model){
		model.addAttribute("copies", service.allCopies());
		return "copies/copies"; 
	}  
	    
	@RequestMapping(value = "copies/{bookId}/{copyId}")
	public Optional<Copy> findById(@PathVariable long bookId, @PathVariable long copyId) {
		
		CopyPK id = new CopyPK(bookId, copyId);
		
		return service.findCopy(id);
	}
	
	@RequestMapping(value = "copies/delete/{bookId}/{copyId}", method = RequestMethod.DELETE)
	public void remove(@PathVariable long bookId, @PathVariable long copyId) {
		CopyPK id = new CopyPK(bookId, copyId);
		
		service.deleteCopy(id);
	}
	
	@RequestMapping(value = "copies/assign/{bookId}/{copyId}/{userId}", method = RequestMethod.PUT)
	public void assignCopy(@PathVariable long bookId, @PathVariable long copyId, @PathVariable long userId) {
		
		// create copy object by combined id
		Optional<Copy> reservedCopy = findById(bookId, copyId);
		
		// abort if selected id doesn't return a copy
		if (reservedCopy.isEmpty()) {
			System.out.println("no copy for id: " + bookId + "." + copyId);
			return;
		}
		
		// create copy-object from list and set new userId
		Copy loanedCopy = reservedCopy.get();
		loanedCopy.setUserId(userId);
		service.updateCopy(loanedCopy);
	}
}
