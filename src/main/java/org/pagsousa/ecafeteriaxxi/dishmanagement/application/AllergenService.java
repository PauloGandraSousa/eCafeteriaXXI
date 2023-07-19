package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Allergen;

/**
 *
 * @author Paulo Gandra Sousa 19/07/2023.
 *
 */
public interface AllergenService {

	/**
	 *
	 * @return
	 */
	Iterable<Allergen> findAll();

	/**
	 *
	 * @return
	 */
	Optional<Allergen> findByName(String name);

}
