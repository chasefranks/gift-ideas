package com.drive2code.gift_ideas.service;

import java.io.File;
import java.util.HashMap;

import com.drive2code.gift_ideas.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TextFilePersonService implements PersonService {
	
	public static final String DEFAULT_FILE = "gift-ideas.json";
	
	private HashMap<String, Person> personsMap = new HashMap<>();

	private String path;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public TextFilePersonService() {		
		this(DEFAULT_FILE);
	}
	
	public TextFilePersonService(String path) {
		
		this.path = path;
		
		File file = new File(path);
		
		if (!file.exists()) {
			personsMap = new HashMap<>();
		} else {
			try {
				personsMap = mapper.readValue(file, new TypeReference<HashMap<String, Person>>() {}); // have to watch for type erasure
			} catch (Exception e) {
				throw new RuntimeException("error parsing file at location " + path + ". Check to ensure the contents is proper json", e);
			}
		}
		
	}

	@Override
	public void add(Person person) {
		personsMap.put(person.getName(), person);
	}

	@Override
	public Person get(String name) {
		return personsMap.get(name);
	}

	@Override
	public boolean exists(String name) {
		return personsMap.containsKey(name);
	}
	
	/**
	 * Writes contents of {@link HashMap} gift ideas to disk.
	 */
	public void flush() {
		
		File file = new File(this.path);	
		
		try {
			mapper.writeValue(file, personsMap);
		} catch (Exception e) {
			throw new RuntimeException("unable to flush gift ideas to disk at location \" + path", e);
		}
		
	}

}
