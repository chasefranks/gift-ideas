package com.drive2code.gift_ideas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

public class TextFileGiftService implements GiftService {
	
	private String path = "gift-ideas.json";
	
	private HashMap<String, List<String>> giftsMap;
	private ObjectMapper mapper = new ObjectMapper();
	
	public TextFileGiftService() throws JsonParseException, JsonMappingException, IOException {
		
		File file = new File(this.path);
		file.createNewFile();
		
		FileInputStream inputStream = new FileInputStream(file);		
		
		try {	
			giftsMap = mapper.readValue(inputStream, HashMap.class);
		} catch (MismatchedInputException e) {
			mapper.writeValue(file, new HashMap<>());
		}
		
		inputStream.close();
		
	}

	public TextFileGiftService(String path) throws Exception {
		
		this.path = path;
		
		File file = new File(path);
		FileInputStream inputStream = new FileInputStream(file);
		
		mapper.writeValue(file, new HashMap<>());
		
		giftsMap = mapper.readValue(inputStream, HashMap.class);
		
		inputStream.close();
		
	}
	
	@Override
	public void add(String who, String what) {
		
		if (!giftsMap.containsKey(who)) {
			ArrayList<String> giftsForPerson = new ArrayList<>();
			giftsMap.put(who, giftsForPerson);
		}
		
		giftsMap.get(who).add(what);
		
		try {
			this.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String get(String who) {
		
		if (!giftsMap.containsKey(who)) {
			giftsMap.put(who, new ArrayList<>());
		}
		
		List<String> gifts = giftsMap.get(who);
		
		StringBuilder giftListAsString = new StringBuilder();
		
		for(String gift: gifts) {
			giftListAsString.append(gift + ", ");
		}
		
		if (giftListAsString.length() == 0) {
			return "";
		} else {
			// length should be greater than or equal to 2
			return giftListAsString.toString().substring(0, giftListAsString.length() - 2);
		}
	
	}

	private void flush() throws JsonGenerationException, JsonMappingException, IOException {
		
		File file = new File(this.path);
		FileOutputStream outputStream = new FileOutputStream(file);
		
		mapper.writeValue(outputStream, giftsMap);
		
		outputStream.close();
		
	}

}
