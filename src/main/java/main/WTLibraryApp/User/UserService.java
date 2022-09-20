package main.WTLibraryApp.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
		User tempUser = repo.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
		users.setPassphrase(tempUser.getPassphrase());
		repo.save(users);
	}

	public void saveUser(User users, long id, String passphrase) {
		if(passphrase.length() > 0) {
			users.setPassphrase(BCrypt.hashpw(passphrase, BCrypt.gensalt()));
			repo.save(users);
		}
		else {
			User tempUser = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
			users.setPassphrase(tempUser.getPassphrase());
			repo.save(users);
		}
	}
	
	public void changePassword(User user, long id) {
		System.out.println("This works!");
	}
	
	public User findUser(long id) {
		User users = repo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id: " + id));
		return users;
	}
	
	public User findByEmail(String email) {
		User users = repo.findByEmail(email)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Email: " + email));
		return users;
	}
	
	public void deleteUser(User user, long id) {
		user.setId(id);
		user.setFirst_name(String.valueOf(id));
		user.setLast_name("Removed");
		user.setActive(false);
		user.setPassphrase(user.getPassphrase());
		repo.save(user);
	}
}
