package com.drive2code.gift_ideas;

/**
 * Represents a service for saving and retrieving gift ideas.
 * 
 * @author chase
 *
 */
public interface GiftService {
	
	/**
	 * Saves a gift idea for a person.
	 * 
	 * @param who the person
	 * @param what the gift
	 */
	public void add(String who, String what);
	
	/**
	 * Retrieves the list of gifts for a person.
	 * 
	 * @param who the person
	 * @return
	 */
	public String get(String who);

	/**
	 * Returns a random gift idea for a person.
	 * 
	 * @param who
	 * @return
	 */
	public String getRandom(String who);

}
