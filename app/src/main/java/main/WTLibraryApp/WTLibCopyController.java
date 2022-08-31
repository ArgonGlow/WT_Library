package main.WTLibraryApp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge=3600)
public class WTLibCopyController {
		
	@Autowired
	private WTLibCopyService service;
	
	
	@RequestMapping(value = "copies")
	public List<Copies> findAll(){
		return service.allCopies();
	}
	
	@RequestMapping(value = "copies/{bookId}/{copyId}")
	public Optional<Copies> findById(@PathVariable long bookId, @PathVariable long copyId) {
		
		CopiesId id = new CopiesId(bookId, copyId);
		
		return service.findCopy(id);
	}
	
	@RequestMapping(value = "copies/delete/{bookId}/{copyId}", method = RequestMethod.DELETE)
	public void remove(@PathVariable long bookId, @PathVariable long copyId) {
		CopiesId id = new CopiesId(bookId, copyId);
		
		service.deleteCopy(id);
	}
	
	@RequestMapping(value = "copies/assign/{bookId}/{copyId}", method = RequestMethod.PUT)
	public void assignCopy(@PathVariable long bookId, @PathVariable long copyId, @RequestBody Copies copy) {
		
		Optional<Copies> reservedCopy = findById(bookId, copyId);
		
		// abort if selected id doesn't return a person
		if (reservedCopy.isEmpty()) {
			System.out.println("no copy for id: " + bookId + "." + "copyId");
			return;
		}
		
		// modify temporary object, ready for updating
		Copies loanedCopy = reservedCopy.get();
		if (loanedCopy.getUserId() == 0) {
			System.out.println("copy already assigned to: " + loanedCopy.getUserId());
			return;
		}
		loanedCopy.setUserId(copy.getUserId());
		service.updateCopy(loanedCopy);
	}
}
