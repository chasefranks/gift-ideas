package com.drive2code.gift_ideas.model;

import java.util.ArrayList;
import java.util.List;

public class Person {
	
	private String name;
	private List<Gift> gifts = new ArrayList<>();
	
	public Person() {}

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Gift> getGifts() {
		return gifts;
	}

	public void setGifts(List<Gift> gifts) {
		this.gifts = gifts;
	}
	
	public void gift(Gift gift) {
		this.gifts.add(gift);
	}

}
