package com.drive2code.gift_ideas.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.drive2code.gift_ideas.model.Person;

public class TextFilePersonServiceTest {
	
	private Random random = new Random();
	
	private String randomFile;
	
	@Before
	public void setUp() throws IOException {
		randomFile = "testing-" + random.nextInt(10000) + ".json";
		System.out.println("using random file at " + randomFile);
	}

	@Test
	public void testAddGet() {
		
		TextFilePersonService personService = new TextFilePersonService(randomFile);
		
		Person expected = new Person("Dr Evil");
		personService.add(expected);
		
		Person actual = personService.get("Dr Evil");
		
		assertNotNull(actual);
		assertEquals(expected, actual);
		
	}

	@Test
	public void testExists() {
		
		TextFilePersonService personService = new TextFilePersonService(randomFile);
		
		Person exists = new Person("Dr Evil");
		personService.add(exists);
		
		assertTrue(personService.exists("Dr Evil"));
		
	}
	
	@After
	public void cleanUp() {
		System.out.println("cleaning up random file at " + randomFile);
		File file = new File(randomFile);
		file.delete();
	}

}
