package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model;

import java.util.Optional;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

/**
 * An organic unit.
 *
 * @author arocha
 * @author Paulo Gandra Sousa
 */
@Entity
public class OrganicUnit implements AggregateRoot<Designation> {

	private static final long serialVersionUID = 1L;

	// database primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk;

	@Version
	private Long version;

	// business identity
	@AttributeOverride(name = "name", column = @Column(name = "acronym", unique = true, nullable = false, length = 50))
	private Designation acronym;

	@AttributeOverride(name = "name", column = @Column(name = "ou_name", unique = true, nullable = false, updatable = true, length = 100))
	private Designation name;

	@AttributeOverride(name = "description", column = @Column(name = "description", nullable = true, updatable = true, length = 512))
	private Description description;

	protected OrganicUnit() {
		// for ORM
	}

	public OrganicUnit(final Designation acronym, final Designation name) {
		Preconditions.noneNull(acronym, name);

		this.acronym = acronym;
		this.name = name;
	}

	@Override
	public Designation identity() {
		return acronym;
	}

	public Designation getAcronym() {
		return acronym;
	}

	public Designation getName() {
		return name;
	}

	public Optional<Description> getDescription() {
		return Optional.ofNullable(description);
	}

	public void setDescription(final Description d) {
		this.description = d;
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
		if (!(other instanceof final OrganicUnit that)) {
			return false;
		}

		if (this == that) {
			return true;
		}

		return acronym.equals(that.acronym) && name.equals(that.name) && description.equals(that.description);
	}

	@Override
	public String toString() {
		return acronym.toString();
	}

	public Long getVersion() {
		return version;
	}
}
