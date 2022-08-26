package books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WTLibBookService {
	
	@Autowired
	private IWTLibBookRepositrory repo;
}
