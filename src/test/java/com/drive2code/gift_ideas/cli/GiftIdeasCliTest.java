package com.drive2code.gift_ideas.cli;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import com.drive2code.gift_ideas.model.Gift;
import com.drive2code.gift_ideas.model.Person;
import com.drive2code.gift_ideas.service.PersonService;

public class GiftIdeasCliTest {

	@Test
	public void testAddStringStringPersonExists() {
		
		// create a mock
		PersonService mockPersonService = mock(PersonService.class);
		
		// stub behavior
		when(mockPersonService.exists("Fat Bastard"))
			.thenReturn(true);
		
		when(mockPersonService.get("Fat Bastard"))
			.thenReturn(new Person("Fat Bastard"));
		
		GiftIdeasCli cli = new GiftIdeasCli(mockPersonService);		
		cli.add("Fat Bastard", "Chilis Baby Back Ribs");
		
	}
	
	@Test
	public void testAddStringStringPersonDoesNotExist() {
		
		PersonService mockPersonService = mock(PersonService.class);
		
		when(mockPersonService.exists("Fat Bastard"))
			.thenReturn(false);
		
		GiftIdeasCli cli = new GiftIdeasCli(mockPersonService);		
		cli.add("Fat Bastard", "Chilis Baby Back Ribs");
		
		Person person = new Person("Fat Bastard");
		person.gift(new Gift("Chilis Baby Back Ribs"));
		
		Mockito.verify(mockPersonService).add(person);
		
	}

//	@Test
//	public void testAddStringStringStringArray() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testTag() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testLink() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetStringString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetRandom() {
//		fail("Not yet implemented");
//	}

}
