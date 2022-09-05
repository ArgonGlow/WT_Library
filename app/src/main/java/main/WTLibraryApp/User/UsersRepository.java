package main.WTLibraryApp.User;

import java.util.Optional;

import javax.activation.DataSource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
