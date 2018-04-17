package com.drive2code.gift_ideas.model;

import java.util.ArrayList;
import java.util.List;

public class Gift {
	
	private String name;
	private List<String> tags = new ArrayList<>();
	private String link;
	
	public Gift() {	}
	
	public Gift(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public void tag(String tag) {
		this.tags.add(tag);
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		StringBuilder giftAsString = new StringBuilder(name);
		giftAsString.append("\n");
		giftAsString.append("tags:");
		
		tags.forEach(tag -> {
			giftAsString.append(" " + tag);
		});
		giftAsString.append("\n");
		
		if (link != null) {
			giftAsString.append("link: " + link);
		}
		
		return giftAsString.toString();
	}

}
