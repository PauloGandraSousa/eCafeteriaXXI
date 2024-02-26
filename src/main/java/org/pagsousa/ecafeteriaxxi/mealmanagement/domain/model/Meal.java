package org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model;

import java.time.LocalDate;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.Cafeteria;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

/**
 * A meal.
 *
 * @author Paulo Gandra Sousa
 *
 */
@Entity
public class Meal implements AggregateRoot<Long> {

	private static final long serialVersionUID = 1L;

	/**
	 * In this case we are showcasing the use of the database generated identity as
	 * an opaque business identity. This should be very well thought off as we are
	 * coupling a business concept with an implementation detail and it should be
	 * avoided. However, in scenarios where an opaque Identifier is needed it is one
	 * possible solution.
	 * <p>
	 * See also the use of UUID as an opaque identity
	 */
	@Id
	@GeneratedValue
	private Long id;

	@Version
	private Long version;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MealType mealType;

	// renamed to "ofday" as "day" is a reserved word in H2 2.1.x
	@Column(nullable = false, name = "ofday")
	private LocalDate day;

	@ManyToOne(optional = false)
	private Cafeteria cafeteria;

	@ManyToOne(optional = false)
	private Dish dish;

	protected Meal() {
		// for ORM
	}

	/**
	 *
	 * @param mealType
	 * @param ofDay
	 * @param dish
	 * @param theMenu
	 */
	public Meal(final MealType mealType, final LocalDate ofDay, final Cafeteria cafeteria, final Dish dish) {
		Preconditions.noneNull(mealType, ofDay, cafeteria, dish);

		this.mealType = mealType;
		day = ofDay;
		this.cafeteria = cafeteria;
		this.dish = dish;
	}

	@Override
	public boolean equals(final Object o) {
		return DomainEntities.areEqual(this, o);
	}

	@Override
	public int hashCode() {
		return DomainEntities.hashCode(this);
	}

	public Long getId() {
		return id;
	}

	public MealType getMealType() {
		return mealType;
	}

	public LocalDate getDay() {
		return day;
	}

	public Cafeteria getCafeteria() {
		return cafeteria;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(final Dish d) {
		Preconditions.nonNull(d);

		this.dish = d;
	}

	@Override
	public Long identity() {
		return id;
	}

	@Override
	public boolean sameAs(final Object other) {
		if (!(other instanceof final Meal that)) {
			return false;
		}

		if (this == that) {
			return true;
		}

		return identity().equals(that.identity()) && day.equals(that.day) && dish.sameAs(that.dish)
				&& mealType.equals(that.mealType) && this.cafeteria.equals(that.cafeteria);
	}

	@Override
	public String toString() {
		return dish + " @ " + day + " / " + mealType;
	}

	public Long getVersion() {
		return version;
	}
}
