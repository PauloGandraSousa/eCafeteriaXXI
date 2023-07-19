package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Allergen;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.AllergenRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * The typical architecture of a REST resource uses a single Service to handle
 * all the use cases. Alternatively we could use a separate use case controller
 * class for each operation.
 *
 * @author Paulo Gandra Sousa 19/07/2023.
 *
 */
@Service
@RequiredArgsConstructor
public class AllergenServiceImpl implements AllergenService {

	private final AllergenRepository repo;

	@Override
	public Iterable<Allergen> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Allergen> findByName(final String name) {
		return repo.findByShortName(name);
	}

}
