package main.WTLibraryApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin(maxAge=3600)
public class UserController {
	
	@Autowired
	private WTUserService service;
	
//	Returns all users from the users table.
	
	@GetMapping("/users")
	public String showUsers(Model model) {
		model.addAttribute("users", service.findAllUsers());
		return "/users/users";
	}
	
//	Adds a new user to the users table
	
	@GetMapping("/users/add-user")
	public String addUser(Users users) {
		return "/users/add-user";
	}
	
	@PostMapping("/users/add-user")
	public String addUserPost(Users users, BindingResult result, Model model) {
		users.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
		if (result.hasErrors()) {
			return "/users/add-user";
		}
		service.saveUser(users);
		return "redirect:/users";
	}
	
//	Updates an user from the users table
	
	@GetMapping("/users/edit-user/{id}")
	public String updateUser(@PathVariable("id") long id, Model model) {
		Users users = service.findUser(id);
		
		model.addAttribute("users", users);
		return "users/edit-user";
	}
	
	@PostMapping("/users/edit-user/{id}")
	public String updateUserPost(@PathVariable("id") long id, Users users, BindingResult result, Model model) {
		if (result.hasErrors()) {
			users.setUser_id(id);
			return "users/edit-user";
		}
		
		service.saveUser(users);
		return "redirect:/users";
	}
	
//	Deletes an user from the table.
	
	@GetMapping("/users/delete-user/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		Users users = service.findUser(id);
		service.deleteUser(users);
		return "redirect:/users";
	}    
	
}
