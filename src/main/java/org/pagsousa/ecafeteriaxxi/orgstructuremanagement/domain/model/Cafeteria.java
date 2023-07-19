package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model;

import java.util.Optional;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.validations.Preconditions;

/**
 * A Cafeteria.
 *
 * @author Paulo Gandra Sousa
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "cafe_name", "unit_name" }) })
public class Cafeteria implements AggregateRoot<CafeteriaName> {

	private static final long serialVersionUID = -6763256902584926321L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pk;

	@Version
	private Long version;

	/**
	 * business identity. we need to ensure the uniqueness of the cafeteria name,
	 * hence the unique constraint declared at the top of the class
	 */
	private CafeteriaName name;

	@AttributeOverride(name = "name", column = @Column(name = "description", nullable = true, length = 512))
	private Description description;

	@ManyToOne(optional = false)
	private OrganicUnit organicUnit;

	protected Cafeteria() {
		// for ORM
	}

	public Cafeteria(final OrganicUnit unit, final Designation name) {
		Preconditions.noneNull(name, unit);

		this.name = new CafeteriaName(unit.identity(), name);
		organicUnit = unit;
	}

	@Override
	public boolean sameAs(final Object other) {
		// TODO Auto-generated method stub
		return false;
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
	public CafeteriaName identity() {
		return name;
	}

	public Optional<Description> getDescription() {
		return Optional.ofNullable(description);
	}

	public void setDescription(final Description d) {
		this.description = d;
	}

	public OrganicUnit unit() {
		return organicUnit;
	}

	public CafeteriaName name() {
		return name;
	}
}
