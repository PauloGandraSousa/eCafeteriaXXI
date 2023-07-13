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
class DishBuilderTest {

	private static final Designation BACALHAU_NAME = Designation.valueOf("Bacalhau");
	private static final DishType FISH_DISH_TYPE = new DishType(DishTypeAcronym.valueOf("fish"),
			Description.valueOf("fishy dishes"));
	private static final Description BACALHAU_DESC = Description
			.valueOf("Receita que não sabemos bem qual o nome mas é deliciosa.");

	@Test
	void ensureCanBuildDishWithoutNutricionalInfo() {
		final var subject = new DishBuilder().ofType(FISH_DISH_TYPE).named(BACALHAU_NAME).costing(Money.euros(7))
				.describedBy(BACALHAU_DESC).build();
		assertNotNull(subject);
	}

	@Test
	void ensureCanBuildWithNutricionalInfo() {
		final var subject = new DishBuilder().ofType(FISH_DISH_TYPE).named(BACALHAU_NAME).costing(Money.euros(7))
				.describedBy(BACALHAU_DESC).withNutricionalInfo(new NutricionalInfo(1, 1)).build();
		assertNotNull(subject);
	}

	@Test
	void ensureCannotBuildWithNullNutricionalInfo() {
		final var builder = new DishBuilder().ofType(FISH_DISH_TYPE).named(BACALHAU_NAME).costing(Money.euros(7));
		assertThrows(IllegalArgumentException.class, () -> builder.withNutricionalInfo(null));
	}

	@Test
	void ensureCannotBuildNutricionalInfoWithNullDishType() {
		final var ni = new NutricionalInfo(1, 1);
		final var builder = new DishBuilder().ofType(null).named(BACALHAU_NAME).costing(Money.euros(7));
		assertThrows(IllegalStateException.class, () -> builder.withNutricionalInfo(ni));
	}

	@Test
	void ensureCannotBuildNutricionalInfoWithoutDishType() {
		final var ni = new NutricionalInfo(1, 1);
		final var builder = new DishBuilder().named(BACALHAU_NAME).costing(Money.euros(7));
		assertThrows(IllegalStateException.class, () -> builder.withNutricionalInfo(ni));
	}

	@Test
	void ensureCannotBuildNutricionalInfoWithoutName() {
		final var ni = new NutricionalInfo(1, 1);
		final var builder = new DishBuilder().ofType(FISH_DISH_TYPE).costing(Money.euros(7));
		assertThrows(IllegalStateException.class, () -> builder.withNutricionalInfo(ni));
	}

	@Test
	void ensureCannotBuildWithNullPrice() {
		final var builder = new DishBuilder().ofType(FISH_DISH_TYPE).named(BACALHAU_NAME).costing(null);
		assertThrows(IllegalStateException.class, () -> builder.build());
	}

	@Test
	void ensureCannotBuildWithNullPrice2() {
		final var builder = new DishBuilder().ofType(FISH_DISH_TYPE).named(BACALHAU_NAME);
		assertThrows(IllegalStateException.class, () -> builder.build());
	}
}
