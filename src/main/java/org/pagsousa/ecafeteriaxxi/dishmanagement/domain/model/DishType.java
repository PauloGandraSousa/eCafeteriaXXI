/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.EqualsBuilder;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.validations.Preconditions;

/**
 * A dish type, e.g., vegetarian or fish or meat.
 *
 * @author Paulo Gandra Sousa 05/07/2023.
 * @author MCN 29/03/2016.
 */
@Entity
public class DishType implements AggregateRoot<DishTypeAcronym>, Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ORM primary key. This is an implementation detail and is never exposed to the
	 * outside of the class.
	 */
	@Id
	@GeneratedValue
	private Long pk;

	/**
	 * optimistic locking
	 */
	@Version
	private Long version;

	/**
	 * Business identity. Needs to be unique, thus we add the unique constraint on
	 * the table.
	 */
	private DishTypeAcronym acronym;

	/**
	 * mandatory
	 */
	@AttributeOverride(name = "value", column = @Column(name = "shortDescription", nullable = false, length = 256))
	private Description shortDescription;

	/**
	 * optional
	 */
	@AttributeOverride(name = "value", column = @Column(name = "longDescription", nullable = true, length = 2048))
	private Description longDescription;

	private boolean active;

	protected DishType() {
		// for ORM
	}

	/**
	 * DishType constructor.
	 *
	 * @param acronym          Mandatory
	 * @param shortDescription Mandatory
	 */
	public DishType(final DishTypeAcronym acronym, final Description shortDescription) {
		setAcronym(acronym);
		setShortDescription(shortDescription);
		// the business rules state a dish type is active by default
		active = true;
	}

	/**
	 * DishType constructor. this would be a nice constructor to have. MapStruct
	 * requires a single public constructor or one marked as @Default, thus we
	 * decided to hide this constructor as protected
	 *
	 * @param acronym          Mandatory
	 * @param shortDescription Mandatory
	 */
	protected DishType(final DishTypeAcronym acronym, final Description shortDescription,
			final Description longDescription) {
		this(acronym, shortDescription);
		setLongDescription(longDescription);
	}

	/**
	 * Sets and validates newDescription.
	 *
	 *
	 * @param newDescription
	 */
	public void setShortDescription(final Description newDescription) {
		// the business rules states a short description is mandatory
		Preconditions.nonNull(newDescription);
		shortDescription = newDescription;
	}

	/**
	 * Sets and validates newName. this is a private method and should never be made
	 * public since it is the business identity of the object
	 *
	 * @param newName The new DishType name.
	 */
	private void setAcronym(final DishTypeAcronym newName) {
		// the business rules states an acronym is mandatory
		Preconditions.nonNull(newName);
		acronym = newName;
	}

	/**
	 *
	 * @return
	 */
	public Description getShortDescription() {
		return shortDescription;
	}

	/**
	 *
	 * @return
	 */
	public Optional<Description> getLongDescription() {
		return Optional.ofNullable(longDescription);
	}

	/**
	 *
	 * @return
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Toggles the state of the dish type, activating it or deactivating it
	 * accordingly.
	 *
	 * @return whether the dish type is active or not
	 */
	public boolean toogleState() {
		active = !active;
		return isActive();
	}

	/**
	 *
	 * @param longDescription
	 */
	public void setLongDescription(final Description longDescription) {
		// a long description is optional, so it might be null without a problem
		this.longDescription = longDescription;
	}

	@Override
	public DishTypeAcronym identity() {
		return acronym;
	}

	public DishTypeAcronym getAcronym() {
		return identity();
	}

	/**
	 * for optimistic locking control
	 *
	 * @return
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * since this a DDD entity we only need to check for identity equality
	 */
	@Override
	public boolean equals(final Object o) {
		return DomainEntities.areEqual(this, o);
	}

	/**
	 * if for some reason we need to compare all the values of the entity
	 */
	@Override
	public boolean sameAs(final Object other) {
		if (!(other instanceof DishType)) {
			return false;
		}

		final var that = (DishType) other;
		return new EqualsBuilder().append(active, that.active).append(shortDescription, that.shortDescription)
				.append(longDescription, that.longDescription).append(acronym, that.acronym).isEquals();
	}

	@Override
	public int hashCode() {
		return DomainEntities.hashCode(this);
	}

	@Override
	public String toString() {
		return acronym.toString();
	}
}
