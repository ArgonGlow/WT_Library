package main.WTLibraryApp.Book.Copy;

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
public class CopiesController {
		
	@Autowired
	private CopiesService service;
	
	
	@RequestMapping(value = "copies")
	public List<Copies> findAll(){
		return service.allCopies();
	}
	
	@RequestMapping(value = "copies/{bookId}/{copyId}")
	public Optional<Copies> findById(@PathVariable long bookId, @PathVariable long copyId) {
		
		CopiesPK id = new CopiesPK(bookId, copyId);
		
		return service.findCopy(id);
	}
	
	@RequestMapping(value = "copies/delete/{bookId}/{copyId}", method = RequestMethod.DELETE)
	public void remove(@PathVariable long bookId, @PathVariable long copyId) {
		CopiesPK id = new CopiesPK(bookId, copyId);
		
		service.deleteCopy(id);
	}
}
