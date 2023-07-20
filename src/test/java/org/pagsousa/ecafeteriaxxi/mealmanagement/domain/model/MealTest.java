package org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.Cafeteria;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.OrganicUnit;

import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.money.domain.model.Money;

/**
 *
 * @author Paulo Gandra Sousa 17/07/2023
 *
 */
class MealTest {

	private final Description aShortDescription = Description.valueOf("uma descrição");
	private final DishTypeAcronym anAcronym = DishTypeAcronym.valueOf("acronimo");
	private final DishType aDishType = new DishType(anAcronym, aShortDescription);
	private final Designation aDesignation = Designation.valueOf("nome do prato");
	private final Money aPrice = Money.euros(2.99);
	private final Dish aDish = new Dish(aDishType, aDesignation, aPrice, aShortDescription);
	private final OrganicUnit anOU = new OrganicUnit(Designation.valueOf("ISEP"), Designation.valueOf("ISEP"));
	private final Cafeteria aCafeteria = new Cafeteria(anOU, Designation.valueOf("cantina"));

	@Test
	void ensureMealMustHaveDish() {
		assertThrows(IllegalArgumentException.class, () -> new Meal(MealType.LUNCH, LocalDate.now(), aCafeteria, null));
	}

	@Test
	void ensureMealMustHaveDate() {
		assertThrows(IllegalArgumentException.class, () -> new Meal(MealType.LUNCH, null, aCafeteria, aDish));
	}

	@Test
	void ensureMealMustHaveMealType() {
		assertThrows(IllegalArgumentException.class, () -> new Meal(null, LocalDate.now(), aCafeteria, aDish));
	}

	@Test
	void ensureMealMustHaveCafeteria() {
		assertThrows(IllegalArgumentException.class, () -> new Meal(MealType.LUNCH, LocalDate.now(), null, aDish));
	}
}
