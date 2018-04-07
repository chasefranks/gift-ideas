package com.drive2code.gift_ideas;

import java.io.IOException;

import com.drive2code.gift_ideas.cli.GiftIdeasCli;
import com.drive2code.gift_ideas.service.GiftService;
import com.drive2code.gift_ideas.service.TextFileGiftService;

import asg.cliche.ShellFactory;

/**
 * Main launcher for this application.
 * 
 * @author chase
 *
 */
public class GiftIdeasApp {

	public static void main(String[] args) throws IOException {
		
		GiftService giftService;
		
		if (args.length > 0) {
			String giftFilePath = args[0];
			System.out.println("Gift ideas will be saved in file located at " + giftFilePath);
			giftService = new TextFileGiftService(giftFilePath);
		} else {
			System.out.println("Gift ideas will be saved in file located at ./" + TextFileGiftService.DEFAULT_FILE);
			giftService = new TextFileGiftService();
		}
		
		ShellFactory.createConsoleShell("gift-ideas", "", new GiftIdeasCli(giftService))
			.commandLoop();
	}

}
