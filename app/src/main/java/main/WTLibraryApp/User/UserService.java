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

	public void saveUser(User users, long id) {
		users.setUser_id(id);
		repo.save(users);
	}

	public User findUser(long id) {
		User users = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
		return users;
	}

	public void deleteUser(User users, long id) {
		users.setUser_id(id);
		users.setFirst_name("unknown");
		users.setLast_name("unknown");
		users.setEmail("unknown_" + users.getUser_id() + "@wt.library");
		users.setPassword("$2y$10$xLZIXf1sMgwM2o8UTkHmj.MndZMOj7jyk8U1TaEYrS2cXTdl92WEe");
		users.setActive(0);
		repo.save(users);
	}

}
