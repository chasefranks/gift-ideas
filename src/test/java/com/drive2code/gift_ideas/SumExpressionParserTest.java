package com.drive2code.gift_ideas;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SumExpressionParserTest {

	@Test
	public void testParse() {
		
		SumExpressionParser parser = new SumExpressionParser();
		
		// expect {1, 2, 3}
		List<Integer> actualNumbers = parser.parse("  1 + 2 + 3  ");
		List<Integer> expectedNumbers = Arrays.asList(1,2,3);
		
		assertEquals(expectedNumbers, actualNumbers);
		
	}
	
	@Test
	public void testParseWithEmpty() {
		SumExpressionParser parser = new SumExpressionParser();
		
		List<Integer> actualNumbers = parser.parse("");
		List<Integer> expectedNumbers = Arrays.asList();
		
		assertEquals(expectedNumbers, actualNumbers);
		
	}

}
