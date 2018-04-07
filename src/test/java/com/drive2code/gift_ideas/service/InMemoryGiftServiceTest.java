package com.drive2code.gift_ideas.service;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.drive2code.gift_ideas.service.GiftService;
import com.drive2code.gift_ideas.service.InMemoryGiftService;

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
	
	@Test
	public void testGetRandom() {
		
		GiftService inMemoryGiftService = new InMemoryGiftService();
		inMemoryGiftService.add("Chase", "headphones");
		inMemoryGiftService.add("Chase", "beer");
		inMemoryGiftService.add("Chase", "laptop");
		
		String randomGift = inMemoryGiftService.getRandom("Chase");
		
		assertNotNull(randomGift);
		assertTrue("random gift isn't contained in list of gifts", Arrays.asList("headphones", "beer", "laptop").contains(randomGift));
		
	}

}
