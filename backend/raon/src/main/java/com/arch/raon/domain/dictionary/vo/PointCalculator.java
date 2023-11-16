package com.arch.raon.domain.dictionary.vo;

public class PointCalculator {
	public static int getPoint(int time_spend_millisecond) {
		double scaledValue = (double)(time_spend_millisecond - 1000) / (20000 - 1000);
		double result = 100 * (1 - Math.pow(scaledValue, 0.5));

		return (int)Math.round(result);

		//return 100 - (time_spend_millisecond * time_spend_millisecond / (20_00*20_00));
	}
}
