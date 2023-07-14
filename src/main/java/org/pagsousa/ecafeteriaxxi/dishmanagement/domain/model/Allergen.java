package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;

/**
 * An Allergen.
 * <p>
 * This example uses "primitive" java types (e.g., int, String) instead of value
 * object classes
 *
 * @author mcn
 * @author Paulo Gandra de Sousa
 *
 */
@Entity
public class Allergen implements AggregateRoot<String>, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ORM primary key
	 */
	@Id
	@GeneratedValue
	private Long pk;

	@Version
	private Long version;

	/**
	 * Business identity. Mandatory and unique, thus the unique and not nullable
	 * constraint.
	 */

	@Column(unique = true, nullable = false)
	private final String shortName;

	@Column(nullable = false)
	private final String fullName;

	@JsonIgnore
	@XmlTransient
	@Lob
	private byte[] image;

	protected Allergen() {
		// for ORM
		shortName = null;
		fullName = null;
	}

	/**
	 *
	 * @param shortName
	 * @param fullName
	 * @param image
	 */
	public Allergen(final String shortName, final String fullName, final byte[] image) {
		this(shortName, fullName);

		changeImage(image);
	}

	/**
	 *
	 * @param shortName
	 * @param fullName
	 */
	public Allergen(final String shortName, final String fullName) {
		Preconditions.ensure(fullNameMeetsMinimumRequirements(fullName));
		Preconditions.ensure(shortNameMeetsMinimumRequirements(shortName));

		this.fullName = fullName;
		this.shortName = shortName;
	}

	/**
	 * Ensure name is not null or empty.
	 * <p>
	 * This is duplicate code that would be avoided if we were using the ShortName
	 * class instead of String.
	 *
	 * @param name
	 * @return {@code true} if name meets minimum requirements. {@code false}
	 *         otherwise.
	 */
	private boolean shortNameMeetsMinimumRequirements(final String name) {
		return !StringPredicates.isNullOrEmpty(name);
	}

	/**
	 * Ensure description is not null or empty.
	 *
	 * <p>
	 * This is duplicate code that would be avoided if we were using the Designation
	 * class instead of String.
	 *
	 * @param description
	 * @return True if description meets minimum requirements. False if description
	 *         does not meet minimum requirements.
	 */
	private boolean fullNameMeetsMinimumRequirements(final String name) {
		return !StringPredicates.isNullOrEmpty(name);
	}

	public String shortName() {
		return shortName;
	}

	public String fullName() {
		return fullName;
	}

	@Override
	public String identity() {
		return shortName;
	}

	public byte[] image() {
		// defensive copy
		return Arrays.copyOf(image, image.length);
	}

	public boolean hasImage() {
		return image != null && image.length != 0;
	}

	/**
	 *
	 * @param image
	 */
	public void changeImage(final byte[] image) {
		Preconditions.nonNull(image);
		// defensive copy
		this.image = Arrays.copyOf(image, image.length);
	}

	@Override
	public boolean sameAs(final Object other) {
		if (!(other instanceof Allergen)) {
			return false;
		}
		final var allergean = (Allergen) other;
		return this.equals(allergean) && fullName.equals(allergean.fullName());
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
	public boolean hasIdentity(final String name) {
		return name.equalsIgnoreCase(shortName);
	}
}
