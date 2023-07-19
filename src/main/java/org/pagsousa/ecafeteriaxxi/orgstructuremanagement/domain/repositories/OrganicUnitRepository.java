package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.repositories;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.OrganicUnit;

import eapli.framework.general.domain.model.Designation;

/**
 *
 * @author arocha
 * @author Paulo Gandra Sousa
 */
public interface OrganicUnitRepository {

	/**
	 *
	 * @param acronym
	 * @return
	 */
	Optional<OrganicUnit> findByAcronym(Designation acronym);

	/**
	 *
	 * @return
	 */
	Iterable<OrganicUnit> findAll();

	/**
	 *
	 * @param ou
	 * @return
	 */
	OrganicUnit save(OrganicUnit ou);
}
