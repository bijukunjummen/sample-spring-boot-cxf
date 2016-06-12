package org.bk.calc.endpoint;

import org.example.ICalculator;

public class CalculatorEndpoint implements ICalculator {
	@Override
	public Integer add(Integer a, Integer b) {
		return a + b;
	}

	@Override
	public Integer subtract(Integer a, Integer b) {
		return a - b;
	}
}
