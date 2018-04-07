package com.drive2code.gift_ideas.cli;

import com.drive2code.gift_ideas.service.GiftService;

import asg.cliche.Command;

/**
 * This class contains the {@link Command} methods bound to
 * commands typed in the Cliche shell.
 * 
 * @author chase
 */
public class GiftIdeasCli {
	
	// dependency is injected through constructor
	private GiftService giftService;
	
	public GiftIdeasCli(GiftService giftService) {
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
	
	@Command(
		description = "retrieve a random gift for someone",
		header = "random gift idea: "
	)
	public String getRandom(String who) {
		return giftService.getRandom(who);
	}

}
