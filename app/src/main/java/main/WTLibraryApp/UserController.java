package main.WTLibraryApp;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin(maxAge=3600)
public class UserController {
	
	@Autowired
	private WTUserService service;
	
	@Autowired
	private IWTUsersRepository usersRepository;
	
//	Returns all users from the users table.
	
	@GetMapping("/users")
	public String showUsers(Model model) {
		model.addAttribute("users", usersRepository.findAll());
		return "/users/users";
	}
	
//	Adds a new user to the users table
	
	@GetMapping("/users/add-user")
	public String addUser(Users users) {
		return "/users/add-user";
	}
	
	@PostMapping("/users/add-user")
	public String addUserPost(Users users, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "/users/add-user";
		}
		usersRepository.save(users);
		return "redirect:/users";
	}
	
//	Updates an user from the users table
	
	@GetMapping("/users/edit-user/{id}")
	public String updateUser(@PathVariable("id") long id, Model model) {
		Users users = usersRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
		
		model.addAttribute("users", users);
		return "users/edit-user";
	}
	
	@PostMapping("/users/edit-user/{id}")
	public String updateUserPost(@PathVariable("id") long id, Users users, BindingResult result, Model model) {
		if (result.hasErrors()) {
			users.setUser_id(id);
			return "users/edit-user";
		}
		
		usersRepository.save(users);
		return "redirect:/users";
	}
	
//	Deletes an user from the table.
	
	@GetMapping("/users/delete-user/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Users users = usersRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
		usersRepository.delete(users);
		return "redirect:/users";
	}
	
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
