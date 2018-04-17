package com.drive2code.gift_ideas.cli;

import java.util.List;
import java.util.Random;

import com.drive2code.gift_ideas.model.Gift;
import com.drive2code.gift_ideas.model.Person;
import com.drive2code.gift_ideas.service.PersonService;

import asg.cliche.Command;
import asg.cliche.Param;

/**
 * This class contains the {@link Command} methods bound to
 * commands typed in the Cliche shell.
 * 
 * @author chase
 */
public class GiftIdeasCli {
	
	private PersonService personService;
	
	public GiftIdeasCli(PersonService personService) {
		this.personService = personService;
	}
	
	@Command(
		description = "add a gift idea for someone"
	)
	public void add(@Param(name = "who") String who, @Param(name = "gift") String what) {	
		
		System.out.println(String.format("adding gift idea %s for %s", what, who));
		
		Gift g = new Gift(what);
		
		if (!personService.exists(who)) {
			Person p = new Person(who);			
			p.gift(g);
			
			personService.add(p);
		} else {
			personService.get(who).gift(g);
		}		
		
	}
	
	@Command(
		description = "add a gift idea for someone"
	)
	public void add(@Param(name = "who") String who, @Param(name = "gift") String what, @Param(name = "tags") String... tags) {		
		
		System.out.println(String.format("adding gift idea %s for %s", what, who));
		
		if (tags.length > 0) {
			System.out.print("with tags: ");
			for(String tag : tags)
				System.out.print(tag + " ");
			
			System.out.println();
		}
		
		/*****************
		 * person service
		 * lookup if who exists
		 *  if not
		 *  		create a new Person object
		 *  		create a new gift with possible tags
		 *  		add them
		 *  else
		 *  		create gift with possible tags
		 *  		retrieve the person and add the gift idea using .gift()
		 *  	
		 *****************/
		Gift g = new Gift(what);
		for (String tag: tags) {
			g.tag(tag);
		}
		
		if (!personService.exists(who)) {
			Person p = new Person(who);			
			p.gift(g);
			
			personService.add(p);
		} else {
			personService.get(who).gift(g);
		}		
		
	}

	@Command(
		description = "add a tag to a gift idea"
	)
	public void tag(@Param(name = "who") String who, @Param(name = "gift") String what, @Param(name = "tag") String tag) {
		if (personService.exists(who)) {
			personService.get(who)
				.getGifts()
				.stream()
				.filter(gift -> 	gift.getName().equals(what))
				.forEach(gift -> gift.tag(tag));
		} else {
			System.out.println("person " + who + " doesn't exist");
		}
	}
	
	@Command(
		description = "link a website to a gift idea"
	)	
	public void link(@Param(name = "who") String who, @Param(name = "gift") String what, @Param(name = "website") String link) {
		if (personService.exists(who)) {
			personService.get(who)
				.getGifts()
				.stream()
				.filter(gift -> gift.getName().equals(what))
				.forEach(gift -> gift.setLink(link));
		}
	}
	
	@Command(
		description = "retrieve the list of gifts for someone",
		header = "------------------\ngift ideas:\n------------------"
	)
	public String get(@Param( name = "who") String who) {
		
		StringBuilder giftsAsString = new StringBuilder();
		
		if (!personService.exists(who))
			return "";
		
		int idx = 1;
		for(Gift gift : personService.get(who).getGifts()) {
			giftsAsString.append(Integer.toString(idx++) + ".) ");
			giftsAsString.append(gift);
			giftsAsString.append("\n\n");
		}
		
		return giftsAsString.toString();

	}
	
	@Command(
		description = "retrieve the list of gifts for someone by tag",
		header = "------------------\ngift ideas:\n------------------"
	)
	public String get(@Param( name = "who") String who, @Param(name = "tag") String tag) {
		
		StringBuilder giftsAsString = new StringBuilder();
		
		if (personService.exists(who)) {
			personService.get(who)
				.getGifts()
				.stream()
				.filter(gift -> gift.getTags().contains(tag))
				.forEach(gift -> {
					giftsAsString.append(gift.toString());
					giftsAsString.append("\n\n");
				});
		}		
		
		return giftsAsString.toString();

	}
	
	@Command(
		description = "retrieve a random gift for someone",
		header = "random gift idea: "
	)
	public String getRandom(@Param( name = "who") String who) {
		
		Random random = new Random();
		
		Person p = personService.get(who);
		
		if (p == null) {
			return "";
		} else {
			
			List<Gift> gifts = p.getGifts();
			int numberOfGifts = gifts.size();
			
			if (numberOfGifts == 0)
				return "";
			
			if (numberOfGifts == 1)
				return gifts.get(0).toString();
			
			return gifts.get(random.nextInt(numberOfGifts - 1)).toString();			
			
		}
		
	}

}
