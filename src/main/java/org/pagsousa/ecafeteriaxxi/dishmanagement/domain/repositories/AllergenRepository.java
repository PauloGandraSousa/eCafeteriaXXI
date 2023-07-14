package org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Allergen;

/**
 *
 * @author mcn
 * @author Paulo Gandra de Sousa
 */
public interface AllergenRepository {
	/**
	 *
	 * @return
	 */
	Iterable<Allergen> findAll();

	/**
	 *
	 * @return
	 */
	Optional<Allergen> findById(Long id);

	/**
	 *
	 * @return
	 */
	Optional<Allergen> findByShortName(String name);
}
