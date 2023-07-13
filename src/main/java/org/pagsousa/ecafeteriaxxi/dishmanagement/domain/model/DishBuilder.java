package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model;

import java.util.Set;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.money.domain.model.Money;

/**
 * A Dish builder. Since there are two optional components of Dish, i.e.,
 * nutricional info and allergens, we are using a builder to simplify the class
 * and avoid overloading constructors or too much conditional logic on the
 * constructor.
 *
 * @author Paulo Gandra de Sousa 2021/05/04
 *
 */
public class DishBuilder implements DomainFactory<Dish> {

	private Dish theDish;

	private Designation name;
	private DishType type;
	private Money price;
	private Description shortDescription;
	private Description longDescription;

	public DishBuilder ofType(final DishType dishType) {
		type = dishType;
		return this;
	}

	public DishBuilder named(final Designation name) {
		this.name = name;
		return this;
	}

	public DishBuilder costing(final Money price) {
		this.price = price;
		return this;
	}

	public DishBuilder describedBy(final Description shortDescription) {
		this.shortDescription = shortDescription;
		return this;
	}

	private Dish buildOrThrow() {
		// we will create the actual instance inside the builder during the building
		// process, but that is hidden from the client code. conceptually, the client
		// code only sees the new instance (it is only built) in the build method
		if (theDish != null) {
			return theDish;
		}
		if (name != null && type != null && price != null) {
			theDish = new Dish(type, name, price, shortDescription);
			if (longDescription != null) {
				theDish.setLongDescription(longDescription);
			}
			return theDish;
		}
		throw new IllegalStateException();
	}

	/**
	 *
	 * @param nut
	 * @return
	 * @throws IllegalStateException
	 */
	public DishBuilder withNutricionalInfo(final NutricionalInfo nut) {
		buildOrThrow();
		theDish.changeNutricionalInfoTo(nut);
		return this;
	}

	/**
	 *
	 * @param allergens
	 * @return
	 * @throws IllegalStateException
	 */
	public DishBuilder withAllergens(final Set<Allergen> allergens) {
		// we will simply ignore if we receive a null set
		if (allergens != null) {
			allergens.forEach(this::withAllergen);
		}
		return this;
	}

	/**
	 *
	 * @param allergen
	 * @return
	 * @throws IllegalStateException
	 */
	public DishBuilder withAllergen(final Allergen allergen) {
		buildOrThrow();
		theDish.addAllergen(allergen);
		return this;
	}

	/**
	 * Builds the desired Dish.
	 * <p>
	 * Note that this builder is not reusable and there is no guarantee that calling
	 * build() a second time will produce a Dish according to the specification,
	 * namely in what respects to nutricional info and allergens.
	 */
	@Override
	public Dish build() {
		final var ret = buildOrThrow();
		// make sure we will create a new instance if someone reuses this builder and do
		// not change the previously built dish.
		theDish = null;
		return ret;
	}

}
