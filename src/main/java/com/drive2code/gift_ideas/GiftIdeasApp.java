package com.drive2code.gift_ideas;

import java.io.IOException;

import asg.cliche.Command;
import asg.cliche.ShellFactory;

public class GiftIdeasApp {
	
	// dependency is injected through constructor
	private GiftService giftService;
	
	public GiftIdeasApp(GiftService giftService) {
		this.giftService = giftService;
	}
	
	@Command(
		description = "add a gift idea for someone"
	)
	public void add(String who, String what) {		
		System.out.println(String.format("adding gift idea %s for %s", what, who));
		giftService.add(who, what);		
	}
	
	@Command(
		description = "retrieve the list of gifts for someone",
		header = "gift ideas: "
	)
	public String get(String who) {		
		return giftService.get(who);
	}

	public static void main(String[] args) throws IOException {
		// TODO command line option to pass in a text file for saving gift ideas
		ShellFactory.createConsoleShell("gift-ideas", "", new GiftIdeasApp(new TextFileGiftService()))
			.commandLoop();
	}

}
