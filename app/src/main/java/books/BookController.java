package books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(maxAge=3600)
public class BookController {
	
	@Autowired
	private WTLibBookService service;
	
	@RequestMapping(value = "books")
	public List<Books> findAll(){
		return service.AllBooks();
	}

}
