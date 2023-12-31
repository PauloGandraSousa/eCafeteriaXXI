package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.application;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.Cafeteria;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.OrganicUnit;

/**
 *
 * @author Paulo Gandra Sousa 19/07/2023.
 *
 */
public interface OrganicUnitService {

	/**
	 *
	 * @return
	 */
	Iterable<OrganicUnit> findAll();

	/**
	 *
	 * @return
	 */
	Optional<OrganicUnit> findByAcronym(String acronym);

	/**
	 *
	 * @param acronym
	 * @return
	 */
	Iterable<Cafeteria> findCafeteriasByOrgUnit(String acronym);

	/**
	 *
	 * @param acronym
	 * @param cafeName
	 * @return
	 */
	Optional<Cafeteria> findCafeteriaOfOrgUnit(String acronym, String cafeName);

}
