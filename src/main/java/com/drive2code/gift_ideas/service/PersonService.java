package com.drive2code.gift_ideas.service;

import com.drive2code.gift_ideas.model.Person;

public interface PersonService {
	
	public void add(Person person);
	
	public Person get(String name);
	
	public boolean exists(String name);

}
