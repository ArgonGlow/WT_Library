package main.WTLibraryApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWTLibBookRepositrory extends JpaRepository<Books, Long>{

}