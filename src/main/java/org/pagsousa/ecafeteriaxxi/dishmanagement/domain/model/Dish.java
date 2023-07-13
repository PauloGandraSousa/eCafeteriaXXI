package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Version;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishesPerCaloricCategory;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.money.domain.model.Money;
import eapli.framework.validations.Preconditions;

/**
 * A Dish.
 *
 * <p>
 * <img src="dish.svg">
 * </p>
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 * @author Paulo Gandra de Sousa
 *
 *         <!--
 * @startuml dish.svg
 *
 *           package DishAgregate{
 *
 *           class Dish { Designation name; DishType dishType; NutricionalInfo
 *           nutricionalInfo; Money price; boolean active; Set<AllergenInDish>
 *           allergens; }
 *
 *           NutricionalInfo <-down- Dish Dish o-down- AllergenInDish :
 *           allergens }
 *
 *           package DishTypeAgregate{
 *
 *           class DishType }
 *
 *           DishType <-down- Dish
 *
 *           interface DTOable<DishDTO> { DishDTO toDTO(); }
 *
 *           interface Representationable{ +buildRepresentation() } interface
 *           RepresentationBuilder<R>{ +startObject() +endObject()
 *           +startCollection() +endCollection() +withElement() +withProperty()
 *           } Representationable ..> RepresentationBuilder
 *
 *           Dish .up.|> DTOable Dish .up.|> Representationable
 *
 *           Dish .right.> DishDTO DTOable ..> DishDTO
 * @enduml -->
 */
@Entity
@NamedNativeQuery(name = "nativeDishesPerCaloricCategory", query = "SELECT caloricCategory, COUNT(*) as n "
		+ "FROM (SELECT *, CASE WHEN calories <= 150 THEN 'low' WHEN calories > 150 AND calories < 350 THEN 'medium' ELSE 'high' END AS caloricCategory FROM DISH) "
		+ "GROUP BY caloricCategory", resultSetMapping = "DishesPerCaloricCategoryMapping")
@SqlResultSetMapping(name = "DishesPerCaloricCategoryMapping", classes = @ConstructorResult(targetClass = DishesPerCaloricCategory.class, columns = {
		@ColumnResult(name = "caloricCategory"), @ColumnResult(name = "n") }))
public class Dish implements AggregateRoot<Designation> {

	private static final long serialVersionUID = 1L;

	/**
	 * Immutable empty set of allergens for the situations where a dish does not
	 * contain any allergen.
	 */
	public static final Set<Allergen> NO_ALLERGENS = Collections.emptySet();

	@Id
	private UUID id;

	@Version
	private Long version;

	/**
	 * business identity
	 */
	private Designation name;

	/**
	 * cascade = CascadeType.NONE as the dishType is part of another aggregate
	 */
	@ManyToOne(optional = false)
	private DishType dishType;

	@AttributeOverride(name = "value", column = @Column(name = "shortDescription", nullable = false, length = 512))
	private Description shortDescription;

	@AttributeOverride(name = "value", column = @Column(name = "longDescription", nullable = true, length = 2048))
	private Description longDescription;

	private NutricionalInfo nutricionalInfo;

	private boolean active;

	private Money price;

	@ManyToMany
	private final Set<Allergen> allergens = new HashSet<>();

	/**
	 * Constructor.
	 *
	 * @param dishType
	 * @param name
	 * @param price
	 */
	protected Dish(final DishType dishType, final Designation name, final Money price,
			final Description shortDescription) {
		Preconditions.noneNull(dishType, name, price, shortDescription);

		this.dishType = dishType;
		this.name = name;
		this.price = price;
		active = true;
		this.shortDescription = shortDescription;
	}

	protected Dish() {
		// for ORM only
	}

	@Override
	public boolean equals(final Object o) {
		return DomainEntities.areEqual(this, o);
	}

	@Override
	public int hashCode() {
		return DomainEntities.hashCode(this);
	}

	@Override
	public boolean sameAs(final Object other) {
		if (!(other instanceof Dish)) {
			return false;
		}

		final var that = (Dish) other;
		if (this == that) {
			return true;
		}

		var areEqual = identity().equals(that.identity()) && dishType.equals(that.dishType)
				&& shortDescription.equals(that.shortDescription) && nutricionalInfo.equals(that.nutricionalInfo)
				&& price.equals(that.price) && active == that.active && allergens.equals(that.allergens);
		if (longDescription != null) {
			areEqual &= longDescription.equals(that.longDescription);
		}
		return areEqual;
	}

	public DishType dishType() {
		return dishType;
	}

	@Override
	public Designation identity() {
		return name;
	}

	/**
	 *
	 * @return the nutricionalInfo of this dish
	 */
	public Optional<NutricionalInfo> nutricionalInfo() {
		return Optional.ofNullable(nutricionalInfo);
	}

	/**
	 *
	 * @return the dish name
	 */
	public Designation name() {
		return name;
	}

	/**
	 *
	 * @return the current dish price
	 */
	public Money currentPrice() {
		return price;
	}

	/**
	 *
	 * @return true or false whether is or not active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Toggles the state of the dish, activating it or deactivating it accordingly.
	 *
	 * @return whether the dish is active or not
	 */
	public boolean toogleState() {
		active = !active;
		return isActive();
	}

	/**
	 * Changes the nutricional info of the dish.
	 *
	 * @param newNutricionalInfo
	 */
	public void changeNutricionalInfoTo(final NutricionalInfo newNutricionalInfo) {
		Preconditions.nonNull(newNutricionalInfo);
		nutricionalInfo = newNutricionalInfo;
	}

	/**
	 * Changes the dish price to a new price.
	 *
	 * @param newPrice the new price of this dish
	 */
	public void changePriceTo(final Money newPrice) {
		Preconditions.noneNull(newPrice);
		Preconditions.ensure(newPrice.signum() >= 0);

		// TODO extra business logic associated with changing the price of a
		// dish, e.g., save price history
		price = newPrice;
	}

	/**
	 *
	 * @param allergen
	 * @return
	 */
	public boolean addAllergen(final Allergen allergen) {
		return allergens.add(allergen);
	}

	/**
	 *
	 * @return a read-only set of Allergens in Dish
	 */
	public Set<Allergen> allergens() {
		// notice the unmodifiable "copy" we are returning to keep with the Information
		// Expert principle and disallow callers to change "our" attributes
		return Collections.unmodifiableSet(allergens);
	}

	/**
	 * Update the dish's properties. We cannot change the dish's identity.
	 *
	 * @param type
	 * @param active
	 * @param price
	 * @param nutricionalInfo
	 */
	public void update(final DishType type, final boolean active, final Money price,
			final NutricionalInfo nutricionalInfo) {
		Preconditions.noneNull(type, price, nutricionalInfo);

		dishType = type;
		this.active = active;
		changePriceTo(price);
		changeNutricionalInfoTo(nutricionalInfo);
	}

	@Override
	public String toString() {
		return "Dish \"" + name + "\" of type " + dishType;
	}

	public void setLongDescription(final Description longDescription) {
		this.longDescription = longDescription;
	}
}
