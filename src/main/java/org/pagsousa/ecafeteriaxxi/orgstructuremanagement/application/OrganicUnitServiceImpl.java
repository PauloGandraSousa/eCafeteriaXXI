package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.application;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.OrganicUnit;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.repositories.OrganicUnitRepository;
import org.springframework.stereotype.Service;

import eapli.framework.general.domain.model.Designation;
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
public class OrganicUnitServiceImpl implements OrganicUnitService {

	private final OrganicUnitRepository repo;

	@Override
	public Iterable<OrganicUnit> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<OrganicUnit> findByAcronym(final String acronym) {
		return repo.findByAcronym(Designation.valueOf(acronym));
	}

}
