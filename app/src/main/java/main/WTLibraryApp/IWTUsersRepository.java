package main.WTLibraryApp;

import java.util.Optional;

import javax.activation.DataSource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWTUsersRepository extends JpaRepository<Users, Long> {
	Optional<Users> findUserByEmail(String email);
}
