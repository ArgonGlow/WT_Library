package main.WTLibraryApp;

import java.util.List;
import java.util.Optional;

import javax.activation.DataSource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IWTUsersRepository extends JpaRepository<Users, Long> {
	
	@Query(value = "select * from users where first_name like %:keyword% or last_name like %:keyword%", nativeQuery = true)
	List<Users> findByKeyword(@Param("keyword") String keyword);
	
}
