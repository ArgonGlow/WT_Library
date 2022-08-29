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
public class IWTLibCopyController {
	
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
}
