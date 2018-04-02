package com.drive2code.gift_ideas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * An in-memory implementation of {@link GiftService}. Internally it uses a Java
 * {@link HashMap}.
 * 
 * @author chase
 *
 */
public class InMemoryGiftService implements GiftService {
	
	private HashMap<String, List<String>> giftsMap = new HashMap<>();

	@Override
	public void add(String who, String what) {
		
		if (!giftsMap.containsKey(who)) {
			ArrayList<String> giftsForPerson = new ArrayList<>();
			giftsMap.put(who, giftsForPerson);
		}
		
		giftsMap.get(who).add(what);

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
	
	@Override
	public String getRandom(String who) {
		
		// if person isn't in map yet, add them with empty list
		if (!giftsMap.containsKey(who)) {
			giftsMap.put(who, new ArrayList<>());
		}
		
		List<String> gifts = giftsMap.get(who);
		
		int numberOfGifts = gifts.size();
		
		if (numberOfGifts == 0) {
			return "no gifts for " + who + " yet. Use add " + who + " to add gifts for them";
		} else if (numberOfGifts == 1) {
			return gifts.get(0);
		} else {
			 return gifts.get(new Random().nextInt(numberOfGifts - 1));
		}
		
	}

}
