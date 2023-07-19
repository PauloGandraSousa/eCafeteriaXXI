package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.application;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.Cafeteria;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.CafeteriaName;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.OrganicUnit;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.repositories.CafeteriaRepository;
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

	private final OrganicUnitRepository ouRepo;
	private final CafeteriaRepository cafeRepo;

	@Override
	public Iterable<OrganicUnit> findAll() {
		return ouRepo.findAll();
	}

	@Override
	public Optional<OrganicUnit> findByAcronym(final String acronym) {
		return ouRepo.findByAcronym(Designation.valueOf(acronym));
	}

	@Override
	public Iterable<Cafeteria> findCafeteriasByOrgUnit(final String acronym) {
		return cafeRepo.findByOrganicUnit(Designation.valueOf(acronym));
	}

	@Override
	public Optional<Cafeteria> findCafeteriaOfOrgUnit(final String acronym, final String cafeName) {
		return cafeRepo.findByName(CafeteriaName.valueOf(acronym, cafeName));
	}

}
