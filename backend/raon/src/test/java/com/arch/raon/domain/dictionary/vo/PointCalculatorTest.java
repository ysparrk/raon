package com.arch.raon.domain.dictionary.vo;

import org.junit.jupiter.api.Test;

class PointCalculatorTest {

	@Test
	void testPointCalulator(){
		int min = 1000;
		int max = 20000;

		System.out.println(PointCalculator.getPoint(1000));
		System.out.println(PointCalculator.getPoint(20_000));

		for(int i=0; i<10; i++){
			int randomNum = min + (int)(Math.random() * ((max - min) + 1));
			System.out.println("rand:"+ randomNum+" point:"+ PointCalculator.getPoint(randomNum));
		}

	}
}