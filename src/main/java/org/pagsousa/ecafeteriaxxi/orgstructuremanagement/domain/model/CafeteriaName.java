package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.validations.Preconditions;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * A value object to capture the concept of a cafeteria's name; a compound of
 * organic unit + name.
 *
 * @author Paulo Gandra Sousa
 *
 */
@Embeddable
@Value
@Accessors(fluent = true)
public class CafeteriaName implements ValueObject, Comparable<CafeteriaName> {

	private static final long serialVersionUID = 1L;

	@AttributeOverride(name = "name", column = @Column(name = "unit_name", nullable = false, updatable = false))
	private final Designation unit;

	@AttributeOverride(name = "name", column = @Column(name = "cafe_name", nullable = false, updatable = false))
	private final Designation cafe;

	protected CafeteriaName() {
		// for ORM
		unit = null;
		cafe = null;
	}

	public CafeteriaName(final OrganicUnit ou, final Designation name) {
		Preconditions.nonNull(ou);
		Preconditions.nonNull(name, "Cafeterias cannot be empty neither null");

		unit = ou.identity();
		cafe = name;
	}

	public CafeteriaName(final Designation ou, final Designation name) {
		Preconditions.nonNull(ou);
		Preconditions.nonNull(name, "Cafeterias cannot be empty neither null");

		unit = ou;
		cafe = name;
	}

	/**
	 * Factory method. Works the opposite way of {@link #toString()}.
	 *
	 * @param name
	 * @return a cafeteria name object
	 */
	public static CafeteriaName valueOf(final String name) {
		final String tokens[] = name.split("/");
		Preconditions.ensure(tokens.length == 2);
		return new CafeteriaName(Designation.valueOf(tokens[0]), Designation.valueOf(tokens[1]));
	}

	public static CafeteriaName valueOf(final String ouAcronym, final String cafeName) {
		return new CafeteriaName(Designation.valueOf(ouAcronym), Designation.valueOf(cafeName));
	}

	@Override
	public String toString() {
		return unit() + "/" + cafe();
	}

	@Override
	public int compareTo(final CafeteriaName o) {
		return toString().compareTo(o.toString());
	}
}
