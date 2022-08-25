package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@RequestMapping(value = "demo")
	public String demo() {
		return "Hello world!? Why are you ignoring me!?!?!";
	}	
	
	@RequestMapping(value = "demo2")
	public Person demo2() {
		Person p = new Person();
		p.setName("Henk");
		p.setAge(23);
		
		return p;
	}
	
}
