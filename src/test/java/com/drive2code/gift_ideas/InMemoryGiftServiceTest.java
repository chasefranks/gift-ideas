package com.drive2code.gift_ideas;

import static org.junit.Assert.*;

import org.junit.Test;

public class InMemoryGiftServiceTest {
	
	@Test
	public void testAdd() {	
		
		GiftService inMemoryGiftService = new InMemoryGiftService();
		
		inMemoryGiftService.add("Nina", "keychain");
		inMemoryGiftService.add("Nina", "bike");
		inMemoryGiftService.add("Nina", "chandelier");
		
		String giftsForNina = inMemoryGiftService.get("Nina");
		
		assertNotNull(giftsForNina);
		assertEquals("keychain, bike, chandelier", giftsForNina);
		
	}

	@Test
	public void testGetPersonDoesntExistYet() {		
		GiftService inMemoryGiftService = new InMemoryGiftService();		
		assertEquals("", inMemoryGiftService.get("personWhoDoesntExist"));
	}

}
