package main.WTLibraryApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWTLibraryRepository extends JpaRepository<User, Long> {
}
