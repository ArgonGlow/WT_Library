package main.WTLibraryApp.Book.Label;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long>{

	@Transactional
    @Modifying      // to mark delete or update query
    @Query(value = "DELETE from books_labels where books_labels.labels_id = :labelID\r\n",
    		nativeQuery = true)  
    public int deleteRelation(@Param("labelID") long labelID);
}
