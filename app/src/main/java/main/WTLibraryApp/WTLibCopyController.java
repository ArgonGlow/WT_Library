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
	
	@RequestMapping(value = "copies/{id}")
	public Optional<Copies> findById(@PathVariable long id) {
		return service.findCopy(id);
	}
	
	@RequestMapping(value = "copies/delete/{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable long id) {
		service.deleteCopy(id);
	}
	
	@RequestMapping(value = "copies/assign/{id}", method = RequestMethod.PUT)
	public void assignCopy(@PathVariable long id,  @RequestBody Copies copy) {
		Optional<Copies> reservedCopy = findById(id);
		
		// abort if selected id doesn't return a person
		if (reservedCopy.isEmpty()) {
			System.out.println("no copy for id: " + id);
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
