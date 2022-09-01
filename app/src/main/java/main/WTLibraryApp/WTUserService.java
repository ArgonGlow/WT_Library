package main.WTLibraryApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WTUserService {
	
	@Autowired
	private IWTUsersRepository repo;

	public List<Users> findAllUsers() {
		return repo.findAll();
	}
	
	public void saveUser(Users users) {
		repo.save(users);
	}
	
	public Users findUser(long id) {
		Users users = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
		return users;
	}
	
	public void deleteUser(Users users) {
		repo.delete(users);
	}
	
}
