package com.drive2code.gift_ideas;

import java.io.IOException;

import asg.cliche.Command;
import asg.cliche.ShellFactory;

public class GiftIdeasApp {
	
	@Command(
		description = "add a gift idea for someone"
	)
	public void add(String who, String what) {
		System.out.println(String.format("adding gift idea %s for %s", what, who));
	}
	
	@Command(
		description = "retrieve a random gift idea for someone",
		header = "random gift idea: "
	)
	public String get(String who) {
		return "toaster";
	}

	public static void main(String[] args) throws IOException {
		ShellFactory.createConsoleShell("gift-ideas", "", new GiftIdeasApp())
		.commandLoop(); // and three.
	}

}
