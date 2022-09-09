package main.WTLibraryApp.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    @Query(value = "select * from books where title like %:keyword% or author like %:keyword% or isbn like %:keyword%", nativeQuery = true)
    public List<Book> findByKeyword(@Param("keyword") String keyword);
   
}