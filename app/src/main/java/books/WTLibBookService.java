package books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WTLibBookService {
	
	@Autowired
	private IWTLibBookRepositrory repo;
	
	public List<Books> AllBooks(){
		return repo.findAll();
	}
}
