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
		
		displayBanner();
		
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

	/**
	 * Displays a start up banner. Thanks to 
	 * <a href="http://www.bagill.com/ascii-sig.php">www.bagill.com/ascii-sig.php</a> for providing
	 * the tool used to generate this banner.
	 */
	private static void displayBanner() {
		
		StringBuilder banner = new StringBuilder();
		banner.append("  ____ _  __ _     ___    _                " + "\n");
		banner.append(" / ___(_)/ _| |_  |_ _|__| | ___  __ _ ___ " + "\n");
		banner.append("| |  _| | |_| __|  | |/ _` |/ _ \\/ _` / __|" + "\n");
		banner.append("| |_| | |  _| |_   | | (_| |  __/ (_| \\__ \\" + "\n");
		banner.append(" \\____|_|_|  \\__| |___\\__,_|\\___|\\__,_|___/" + "\n");
		
		System.out.println(banner);
		
	}

}
