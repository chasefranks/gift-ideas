package com.drive2code.gift_ideas;

import java.io.IOException;

import com.drive2code.gift_ideas.cli.GiftIdeasCli;
import com.drive2code.gift_ideas.service.TextFilePersonService;

import asg.cliche.ShellFactory;

/**
 * Main launcher for this application.
 * 
 * @author chase
 *
 */
public class GiftIdeasApp {

	public static void main(String[] args) throws IOException {
		
		TextFilePersonService personService;
		
		if (args.length > 0) {
			String giftFilePath = args[0];
			System.out.println("Gift ideas will be saved in file located at " + giftFilePath);
			personService = new TextFilePersonService(giftFilePath);
		} else {
			System.out.println("Gift ideas will be saved in file located at ./" + TextFilePersonService.DEFAULT_FILE);
			personService = new TextFilePersonService();
		}
		
		ShellFactory.createConsoleShell("gift-ideas", "", new GiftIdeasCli(personService))
			.commandLoop();
		
		// flush to text file
		System.out.println("saving gift ideas...");
		personService.flush();
		
		System.out.println("exiting...");
		
	}

}
