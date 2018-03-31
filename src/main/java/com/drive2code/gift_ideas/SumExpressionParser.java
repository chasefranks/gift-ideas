package com.drive2code.gift_ideas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SumExpressionParser {

	public List<Integer> parse(String expression) {
		
		if (expression == null || expression.length() == 0) {
			return Arrays.asList();
		}
		
		// "  1 + 2 + 3  "
		return Stream.of(expression.split("\\+")) // stream of "   1 ", " 2 ", " 3 "
			.map(s -> s.trim()) // stream of "1", "2", "3"
			.map(s -> Integer.parseInt(s)) // stream of 1, 2, 3
			.collect(Collectors.toList()); // collect stream into a list and return
			
	}

}
