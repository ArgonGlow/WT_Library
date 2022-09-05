package main.WTLibraryApp.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findByKeyword(String keyword) {
		return repo.findByKeyword(keyword);
	}

	public List<User> findAllUsers() {
		return repo.findAll();
	}
	
	public void saveUser(User users) {
		repo.save(users);
	}
	
	public User findUser(long id) {
		User users = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
		return users;
	}
	
	public void deleteUser(User users) {
		repo.delete(users);
	}
	
}
