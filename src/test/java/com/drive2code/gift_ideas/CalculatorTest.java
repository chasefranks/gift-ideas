package com.drive2code.gift_ideas;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testEvaluate() {
		
		// mock SumExpressionParser
		SumExpressionParser sumExpressionParser = mock(SumExpressionParser.class);
		
		// stub method behavior
		when(sumExpressionParser.parse("4+6+9"))
			.thenReturn(Arrays.asList(4, 6, 9));
		
		// inject the mock
		Calculator calculator = new Calculator(sumExpressionParser);		
		
		// invoke the method being tested
		int sum = calculator.evaluate("4+6+9");
		
		// assert behavior
		assertEquals(19, sum);
		
	}
	
	@Test
	public void testEvaluateWithEmptyArray() {
		
		SumExpressionParser sumExpressionParser = mock(SumExpressionParser.class);
		
		when(sumExpressionParser.parse(""))
			.thenReturn(Arrays.asList());
		
		Calculator calculator = new Calculator(sumExpressionParser);		
		
		int sum = calculator.evaluate("");
		
		// assert behavior
		assertEquals(0, sum);
		
	}
	
}
