package com.model;

public class Rates {
	/* The bill is calculate based on the following formula: 
	 * UnitsConsumed<100: per unit 0.50
	 * UnitsConsumed between 101 to 200: per unit 0.80
	 * UnitsConsumed>200: per unit 1.50
	 * The admin can change the numbers, so I do not make them constants.
	 */	
	private static Double rateTier1 = 0.5, rateTier2 = 0.8, rateTier3 = 1.5;
	private static Integer thresholdTier1 = 100, thresholdTier2 = 200;
	

	public static Double getRateTier1() {
		return rateTier1;
	}
	public static void setRateTier1(Double rate1) {
		rateTier1 = rate1;
	}
	public static Double getRateTier2() {
		return rateTier2;
	}
	public static void setRateTier2(Double rate2) {
		rateTier2 = rate2;
	}
	public static Double getRateTier3() {
		return rateTier3;
	}
	public static void setRateTier3(Double rate3) {
		rateTier3 = rate3;
	}
	public static Integer getThresholdTier1() {
		return thresholdTier1;
	}
	public static void setThresholdTier1(Integer threshold1) {
		thresholdTier1 = threshold1;
	}
	public static Integer getThresholdTier2() {
		return thresholdTier2;
	}
	public static void setThresholdTier2(Integer threshold2) {
		thresholdTier2 = threshold2;
	}
	
	// cannot use a static method to override the toString() method in the Object class.
	public static String getString() {
		return "Rates [rateTier1=" + rateTier1 + ", rateTier2=" + rateTier2 + ", rateTier3=" + rateTier3
				+ ", thresholdTier1=" + thresholdTier1 + ", thresholdTier2=" + thresholdTier2 + "]";
	}
	public static double calculateCost (int unitsConsumed) {
		double cost = 0.0;
		if (unitsConsumed > thresholdTier2) {
			cost = thresholdTier1*rateTier1 + (thresholdTier2-thresholdTier1)*rateTier2
					+ (unitsConsumed-thresholdTier2)*rateTier3;
		} else if (unitsConsumed > thresholdTier1) {
			cost = thresholdTier1*rateTier1 + (unitsConsumed-thresholdTier1)*rateTier2;
		} else {
			cost = unitsConsumed*rateTier1;
		}		
		return cost;
	}
	
	
}
