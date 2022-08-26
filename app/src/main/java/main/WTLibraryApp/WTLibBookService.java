package main.WTLibraryApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WTLibBookService {
	
	@Autowired
	private IWTLibBookRepositrory repo;
	
	public List<Books> allBooks(){
		return repo.findAll();
	}
}
