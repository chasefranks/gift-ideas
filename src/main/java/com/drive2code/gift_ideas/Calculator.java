package com.drive2code.gift_ideas;

import java.util.List;

public class Calculator {
	
	private SumExpressionParser sumExpressionParser;	

	public Calculator(SumExpressionParser sumExpressionParser) {
		this.sumExpressionParser = sumExpressionParser;
	}

	public int evaluate(String expression) {
		
		int sum = 0;
		
		List<Integer> numbers = sumExpressionParser.parse(expression);	
		
		System.out.println(numbers.size());
		
		for (Integer i : numbers) {
			sum += i;
		}
		
		return sum;
		
	}
	
}
