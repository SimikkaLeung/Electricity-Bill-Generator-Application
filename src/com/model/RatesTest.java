package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RatesTest {

	@Test
	void testCalculateCost() {
		assertEquals(Rates.calculateCost(50), 25);
		assertEquals(Rates.calculateCost(110), 58);
		assertEquals(Rates.calculateCost(210), 145);
	}

}
