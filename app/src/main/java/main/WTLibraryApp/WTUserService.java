package main.WTLibraryApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WTUserService {
	
	@Autowired
	private IWTUsersRepository repo;
	
	public List<Users> findByKeyword(String keyword) {
		return repo.findByKeyword(keyword);
	}
	
	public List<Users> findAllUsers() {
		return repo.findAll();
	}
	
	public void saveUser(Users users) {
		repo.save(users);
	}
	
	public void saveUser(Users users, long id) {
		users.setUser_id(id);
		repo.save(users);
	}
	
	public Users findUser(long id) {
		Users users = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
		return users;
	}
	
	public void deleteUser(Users users, long id) {
		users.setUser_id(id);
		users.setFirst_name("unknown");
		users.setLast_name("unknown");
		users.setEmail("unknown_"+users.getUser_id()+"@wt.library");
		users.setPassword("$2y$10$xLZIXf1sMgwM2o8UTkHmj.MndZMOj7jyk8U1TaEYrS2cXTdl92WEe");
		users.setActive(0);
		repo.save(users);
	}
	
}
