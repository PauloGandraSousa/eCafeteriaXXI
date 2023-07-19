package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.repositories;

import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.Cafeteria;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.OrganicUnit;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface CafeteriaRepository {

	Cafeteria save(Cafeteria cafe);

	/**
	 * Returns the cafeterias of an organic unit.
	 *
	 * @param ou
	 * @return the cafeterias of an organic unit
	 */
	Iterable<Cafeteria> findByOrganicUnit(OrganicUnit ou);
}
