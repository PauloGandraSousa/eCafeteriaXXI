package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 *
 * @author AntónioRocha
 */
class NutricionalInfoTest {

	@Test
	void ensureCaloriesMustNotBeNegative() {
		System.out.println("calories must not be negative");
		assertThrows(IllegalArgumentException.class, () -> new NutricionalInfo(-1, 0));
	}

	@Test
	void ensureSaltMustNotBeNegative() {
		System.out.println("Salt must not be negative");
		assertThrows(IllegalArgumentException.class, () -> new NutricionalInfo(0, -6));
	}

	@Test
	void ensureCanCreateWithSaltAndCalories() {
		assertNotNull(new NutricionalInfo(3, 5));
	}

	@Test
	void ensureCaloriesReturnsTheCalories() {
		System.out.println("calories");
		final var instance = new NutricionalInfo(3, 5);
		assertEquals(3, instance.caloriesInKCalPer100());
	}

	@Test
	void ensureSaltReturnsTheSalt() {
		System.out.println("salt");
		final var instance = new NutricionalInfo(3, 5);
		assertEquals(5, instance.saltInGPer100());
	}
}
