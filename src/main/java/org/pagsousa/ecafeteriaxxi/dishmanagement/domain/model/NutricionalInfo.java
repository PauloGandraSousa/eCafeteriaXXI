package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.Value;
import lombok.experimental.Accessors;

/**
 * The nutricional information of a dish.
 * <p>
 * Both {@code calories} and {@code salt} would be a very interesting situation
 * to use <a href="http://martinfowler.com/eaaDev/quantity.html">Quantity
 * pattern</a>
 * </p>
 * <p>
 * Also check the <a hef=
 * "http://jscience.org/api/javax/measure/package-summary.html">javax.measure</a>
 * javadocs or <a href="https://www.baeldung.com/javax-measure">intro</a>
 * </p>
 *
 * @author Jorge Santos ajs@isep.ipp.pt 11/04/2016
 * @author Paulo Gandra de Sousa
 *
 */
@Embeddable
@Value
@Accessors(fluent = true)
public class NutricionalInfo implements ValueObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Special case value of unknown nutricional info. Even tough
	 * {@link Dish#getNutricionalInfo()} returns an Optional it might be helpful to
	 * have a "Null value".
	 */
	public static final NutricionalInfo UNKNOWN = new NutricionalInfo();

	private final Integer caloriesInKCalPer100;

	private final Integer saltInGPer100;

	/**
	 *
	 * @param calories
	 * @param salt
	 */
	public NutricionalInfo(final int caloriesInKCalPer100, final int saltInGPer100) {
		Preconditions.ensure(caloriesInKCalPer100 >= 0, "Calories must be positive or zero");
		Preconditions.ensure(saltInGPer100 >= 0, "Salt must be positive or zero");

		this.caloriesInKCalPer100 = caloriesInKCalPer100;
		this.saltInGPer100 = saltInGPer100;
	}

	/**
	 * Special case constructor for unknown values and ORM.
	 *
	 */
	protected NutricionalInfo() {
		caloriesInKCalPer100 = saltInGPer100 = -1;
	}

	@Override
	public String toString() {
		return "(" + caloriesInKCalPer100 + " kcal/100, " + saltInGPer100 + " g/100)";
	}
}
