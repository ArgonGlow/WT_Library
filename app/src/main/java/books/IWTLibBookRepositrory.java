package books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWTLibBookRepositrory extends JpaRepository<Book, Long>{

}
