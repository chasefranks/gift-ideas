package com.drive2code.gift_ideas.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.drive2code.gift_ideas.service.GiftService;
import com.drive2code.gift_ideas.service.TextFileGiftService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TextFileGiftServiceTest {
	
	private static Random random = new Random();
	
	private static String randomFile;
	
	@BeforeClass
	public static void setUp() throws IOException {
		randomFile = "testing-" + random.nextInt(10000) + ".json";
		File file = new File(randomFile);
		file.createNewFile();
		
		HashMap<String, List<String>> testGifts = new HashMap<>();
		testGifts.put("David", new ArrayList<>());
		testGifts.get("David").add("worklight");
		testGifts.get("David").add("FarCry5");
				
		new ObjectMapper().writeValue(file, testGifts);
	}
	
	@Test
	public void testGet() throws Exception {
		TextFileGiftService giftService = new TextFileGiftService(randomFile);		
		assertEquals("worklight, FarCry5", giftService.get("David"));		
	}

	@Test
	public void testAdd() throws Exception {
		
		TextFileGiftService giftService = new TextFileGiftService(randomFile);
		giftService.add("Nina", "keychain");	
		giftService.add("Nina", "bike");	
		giftService.add("Nina", "chocolate");
		
		assertEquals("keychain, bike, chocolate", giftService.get("Nina"));
		
	}
	
	@Test
	public void testGetRandom() {
		
		GiftService giftService = new TextFileGiftService(randomFile);
		
		giftService.add("Chase", "headphones");
		giftService.add("Chase", "beer");
		giftService.add("Chase", "laptop");
		
		String randomGift = giftService.getRandom("Chase");
		
		assertNotNull(randomGift);
		assertTrue("random gift isn't contained in list of gifts", Arrays.asList("headphones", "beer", "laptop").contains(randomGift));
		
	}
	
	@Test
	public void testGetRandomWithPersonNotAddedYet() {
		
		GiftService giftService = new TextFileGiftService(randomFile);
		
		String randomGift = giftService.getRandom("Peter");
		
		assertEquals("no gifts for Peter yet. Use add Peter to add gifts for them", randomGift);
		
	}
	
	@Test
	public void testGetRandomForPersonWithOneGift() {
		
		GiftService giftService = new TextFileGiftService(randomFile);
		giftService.add("Paul", "Spring in Action");
		
		String randomGift = giftService.getRandom("Paul");
		
		assertEquals("Spring in Action", randomGift);
		
	}
	
	@AfterClass
	public static void cleanUp() {
		File file = new File(randomFile);
		file.delete();
	}

}
