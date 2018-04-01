package com.drive2code.gift_ideas;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
	public void testConstructor() throws Exception {
		TextFileGiftService giftService = new TextFileGiftService();
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
	
	@AfterClass
	public static void cleanUp() {
		File file = new File(randomFile);
		file.delete();
	}

}
