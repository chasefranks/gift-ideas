package com.drive2code.gift_ideas;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TextFileGiftService implements GiftService {
	
	private static final String DEFAULT_FILE = "gift-ideas.json";
	
	private HashMap<String, List<String>> giftsMap; // Nina -> { keychain, chandelier }
	private ObjectMapper mapper = new ObjectMapper();

	private String path;
	
	public TextFileGiftService() {
		this(DEFAULT_FILE);
	}

	public TextFileGiftService(String path) {
		
		this.path = path;
		
		File file = new File(path);
		
		if (!file.exists()) {
			giftsMap = new HashMap<>();
		} else {
			try {
				giftsMap = mapper.readValue(file, HashMap.class);
			} catch (Exception e) {
				throw new RuntimeException("error parsing file at location " + path + ". Check to ensure the contents is proper json", e);
			}
		}
		
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

	/**
	 * Writes contents of {@link HashMap} gift ideas to disk.
	 */
	private void flush() {		
		File file = new File(this.path);	
		
		try {
			mapper.writeValue(file, giftsMap);
		} catch (Exception e) {
			throw new RuntimeException("unable to flush gift ideas to disk at location \" + path", e);
		}
		
	}

}
