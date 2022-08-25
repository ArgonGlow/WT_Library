package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	
	@Autowired
	private IPersonRepository repo;
	
	public List<Person> vindAllePersonen() {
		return repo.findAll();
	}
	
	public Optional<Person> findByID(long id) {
		return repo.findById(id);
	}
	
	public void save(Person p) {
		repo.save(p);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
