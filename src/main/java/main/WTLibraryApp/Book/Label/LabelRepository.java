package main.WTLibraryApp.Book.Label;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LabelRepository extends JpaRepository<Label,Long>{

}
