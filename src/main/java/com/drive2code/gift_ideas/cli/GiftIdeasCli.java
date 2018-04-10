package com.drive2code.gift_ideas.cli;

import java.util.List;
import java.util.Random;

import com.drive2code.gift_ideas.model.Gift;
import com.drive2code.gift_ideas.model.Person;
import com.drive2code.gift_ideas.service.GiftService;
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
	public void add(String who, String what) {		
		
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
	public void add(String who, String what, String... tags) {		
		
		System.out.println(String.format("adding gift idea %s for %s", what, who));
		
		if (tags.length > 0) {
			System.out.print("with tags: ");
			for(String tag : tags)
				System.out.print(tag + " ");
			
			System.out.println();
		}
		
		/*****************
		 * TODO 
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
		description = "retrieve the list of gifts for someone",
		header = "gift ideas: "
	)
	public String get(String who) {
		
		StringBuilder giftsAsString = new StringBuilder();
		
		if (!personService.exists(who))
			return "";
		
		personService.get(who).getGifts()
			.forEach(gift -> {
				giftsAsString.append(gift);
			});
		
		return giftsAsString.toString();

	}
	
	@Command(
		description = "retrieve a random gift for someone",
		header = "random gift idea: "
	)
	public String getRandom(String who) {
		
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
