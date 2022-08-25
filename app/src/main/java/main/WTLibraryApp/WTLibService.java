package main.WTLibraryApp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WTLibService {
	
	@Autowired
	private IWTLibraryRepository repo;

	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	
	
}
