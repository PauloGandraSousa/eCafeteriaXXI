package org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

/**
 * A meal.
 *
 * @author Paulo Gandra Sousa
 *
 */
@Entity
@Table(name = "MEAL")
public class Meal implements AggregateRoot<Long> {

	private static final long serialVersionUID = 1L;

	/**
	 * In this case we are showcasing the use of the database generated identity as
	 * an opaque business identity. This should be very well thought off as we are
	 * coupling a business concept with an implementation detail and it should be
	 * avoided. However, in scenarios where an opaque Identifier is needed it is one
	 * possible solution.
	 * <p>
	 * See also the use of UUID as an opaque identity for
	 * {@link eapli.ecafeteria.mealbooking.domain.Booking Booking}
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
	public Meal(final MealType mealType, final LocalDate ofDay, final Dish dish) {
		Preconditions.noneNull(mealType, ofDay, dish);

		this.mealType = mealType;
		day = ofDay;
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
		if (!(other instanceof Meal)) {
			return false;
		}

		final var that = (Meal) other;
		if (this == that) {
			return true;
		}

		return identity().equals(that.identity()) && day.equals(that.day) && dish.sameAs(that.dish)
				&& mealType.equals(that.mealType);
	}

	@Override
	public String toString() {
		return dish + " @ " + day + " / " + mealType;
	}

	public Long getVersion() {
		return version;
	}
}
