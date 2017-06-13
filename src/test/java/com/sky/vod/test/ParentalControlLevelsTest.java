package com.sky.vod.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sky.vod.ParentalControlLevels;

public class ParentalControlLevelsTest {

	@Test
	public void testFromString() {
		assertEquals("ParentalControlLevel", ParentalControlLevels.fromString("U"), ParentalControlLevels.PCL_U );
		assertEquals("ParentalControlLevel", ParentalControlLevels.fromString("12"), ParentalControlLevels.PCL_12 );
		assertEquals("ParentalControlLevel", ParentalControlLevels.fromString("15"), ParentalControlLevels.PCL_15 );
		assertEquals("ParentalControlLevel", ParentalControlLevels.fromString("18"), ParentalControlLevels.PCL_18 );
		assertEquals("ParentalControlLevel", ParentalControlLevels.fromString("PG"), ParentalControlLevels.PCL_PG );
		
		assertNull("ParentalControlLevel", ParentalControlLevels.fromString("AB"));
	}

	@Test
	public void testIsEqualToOrLessThan() {
		
		assertFalse(ParentalControlLevels.PCL_15.isEqualToOrLessThan(ParentalControlLevels.PCL_PG));
		
		assertTrue(ParentalControlLevels.PCL_12.isEqualToOrLessThan(ParentalControlLevels.PCL_12));
		
		assertFalse(ParentalControlLevels.PCL_15.isEqualToOrLessThan(ParentalControlLevels.PCL_U));
	}

}
