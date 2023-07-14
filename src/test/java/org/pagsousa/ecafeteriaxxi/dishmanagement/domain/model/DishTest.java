package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.money.domain.model.Money;

/**
 * @author Paulo Gandra Sousa
 *
 */
class DishTest {

	private static final Designation BACALHAU_NAME = Designation.valueOf("Bacalhau à Bráz (ou zé do pipo?)");
	private static final DishType FISH_DISH_TYPE = new DishType(DishTypeAcronym.valueOf("fish"),
			Description.valueOf("fishy dishes"));
	private static final Description BACALHAU_DESC = Description
			.valueOf("Receita que não sabemos bem qual o nome mas é deliciosa.");

	/**
	 * Test method for
	 * {@link eapli.ecafeteria.dishmanagement.domain.model.Dish#Dish(eapli.ecafeteria.dishmanagement.domain.model.DishType, eapli.framework.domain.model.general.Designation, eapli.ecafeteria.dishmanagement.domain.model.NutricionalInfo, eapli.framework.domain.model.general.Money)}.
	 */
	@Test
	void ensureDishWithDishTypeDesignationAndPrice() {
		assertNotNull(new Dish(FISH_DISH_TYPE, BACALHAU_NAME, Money.euros(1.0), BACALHAU_DESC));
	}

	@Test
	void ensureMustHaveDishType() {
		final var price = Money.euros(1.0);
		assertThrows(IllegalArgumentException.class, () -> new Dish(null, BACALHAU_NAME, price, BACALHAU_DESC));
	}

	@Test
	void ensureMustHaveDesignation() {
		final var price = Money.euros(1.0);
		assertThrows(IllegalArgumentException.class, () -> new Dish(FISH_DISH_TYPE, null, price, BACALHAU_DESC));
	}

	@Test
	void ensureMustHavePrice() {
		assertThrows(IllegalArgumentException.class,
				() -> new Dish(FISH_DISH_TYPE, BACALHAU_NAME, null, BACALHAU_DESC));
	}

	@Test
	void ensureMustHaveShortDescription() {
		final var price = Money.euros(1.0);
		assertThrows(IllegalArgumentException.class, () -> new Dish(FISH_DISH_TYPE, BACALHAU_NAME, price, null));
	}

	/**
	 * Test of changeNutricionalInfoTo method, of class Dish.
	 *
	 * PRP - 29.mar.2017
	 */
	@Test
	void ensureCannotChangeNutricionalInfoToNull() {
		System.out.println("ChangeNutricionalInfoTo -New nutricional info must not be null");

		final var subject = buildDish();

		assertThrows(IllegalArgumentException.class, () -> subject.setNutricionalInfo(null));
	}

	private Dish buildDish() {
		return new DishBuilder().ofType(FISH_DISH_TYPE).named(BACALHAU_NAME).costing(Money.euros(7))
				.describedBy(BACALHAU_DESC).withNutricionalInfo(new NutricionalInfo(1, 1)).build();
	}

	/**
	 * Tests of changePriceTo method, of class Dish.
	 *
	 * PRP - 29.mar.2017
	 */
	@Test
	void ensureCannotChangePriceToNull() {
		System.out.println("ChangePriceTo -New price info must not be null");

		final var subject = buildDish();
		assertThrows(IllegalArgumentException.class, () -> subject.setPrice(null));
	}

	@Test
	void ensureCannotChangePriceToNegative() {
		System.out.println("ChangePriceTo -New price can nt be negativel");

		final var price = Money.euros(-1);
		final var subject = buildDish();
		assertThrows(IllegalArgumentException.class, () -> subject.setPrice(price));
	}
}
