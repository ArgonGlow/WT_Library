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
public class UserController {
	
	@Autowired
	private WTUserService service;
	
	/*
	 * Some CRUD templates
	@RequestMapping(value = "person/{id}")
	public Optional<User> findByID(@PathVariable long id) {
		return service.findByID(id);
	}
	
	@RequestMapping(value = "personen")
	public List<User> findAll(){
		return service.vindAllePersonen();
	}
	
	@RequestMapping(value = "person/create", method = RequestMethod.POST)
	public void create(@RequestBody User p) {
		service.save(p);
	}
	
	@RequestMapping(value = "person/alter/{id}", method = RequestMethod.PUT)
	public void alter(@RequestBody User p, @PathVariable long id) {
		User ptje = service.findByID(id).get();
		ptje.setAge(p.getAge());
		ptje.setName(p.getName());
		service.save(ptje);
		
	}
	
	@RequestMapping(value = "person/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable long id) {
		service.delete(id);
	}
	*/
}
