package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.StringMixin;
import eapli.framework.strings.util.StringPredicates;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

/**
 * the acronym for a dish. it must be a single word with no spaces and up to 12
 * characters long.
 * <p>
 * this class is a DDD value object, thus it is immutable (i.e., there are no
 * setters/mutator methods)
 *
 * @author Paulo Gandra de Sousa 06/07/2023
 */
@Embeddable
@EqualsAndHashCode
public final class DishTypeAcronym implements ValueObject, Comparable<DishTypeAcronym>, StringMixin {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = true, length = 12)
	private final String acronym;

	/**
	 * Protected constructor. To construct a new DishTypeAcronym instance use the
	 * {@link #valueOf(String)} method.
	 *
	 * @param acronym
	 */
	private DishTypeAcronym(final String acronym) {
		this.acronym = acronym;
	}

	protected DishTypeAcronym() {
		// for ORM
		acronym = null;
	}

	/**
	 * Factory method for obtaining DishTypeAcronym value objects.
	 * <p>
	 * This factory method constructs the object or throws an exception if it is not
	 * possible to create the value object due to business rules.
	 *
	 * @param name
	 *
	 * @return a new object corresponding to the value
	 *
	 * @throws IllegalArgumentException
	 */
	public static DishTypeAcronym valueOf(final String name) {
		if (!StringPredicates.isSingleWord(name) || name.length() > 12) {
			throw new IllegalArgumentException("Invalid Name");
		}
		return new DishTypeAcronym(name);
	}

	@Override
	public String toString() {
		return acronym;
	}

	@Override
	public int compareTo(final DishTypeAcronym o) {
		return acronym.compareTo(o.acronym);
	}
}
