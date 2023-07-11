package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import java.util.Optional;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishTypeRepository;
import org.pagsousa.ecafeteriaxxi.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * the typical architecture of a REST resource uses a single Service to handle
 * all the use cases. alternatively we could use a separate use case controller
 * class for each operation.
 *
 * @author Paulo Gandra Sousa 06/07/2023.
 *
 */
@Service
@RequiredArgsConstructor
public class DishTypeServiceImpl implements DishTypeService {

	private final DishTypeRepository dishTypeRepo;
	private final CreateOrReplaceDishTypeMapper dishTypeMapper;

	/**
	 *
	 * @return
	 */
	@Override
	public Iterable<DishType> findAll() {
		return dishTypeRepo.findAllActive();
	}

	/**
	 *
	 * @param acronym
	 * @param request
	 * @return
	 */
	@Override
	public DishType create(final String acronym, final CreateOrReplaceDishTypeRequest request) {
		final var dt = dishTypeMapper.create(acronym, request);
		return dishTypeRepo.save(dt);
	}

	/**
	 * partial update
	 *
	 * @param acronym
	 * @param request
	 * @return
	 */
	@Override
	public DishType update(final String acronym, final UpdateDishTypeRequest request, final long expectedVersion) {
		throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
	}

	/**
	 * full update
	 *
	 * @param acronym
	 * @param request
	 * @return
	 */
	@Override
	public DishType replace(final String acronym, final CreateOrReplaceDishTypeRequest request,
			final long expectedVersion) {
		// first let's check if the object exists so we don't create a new object with
		// save
		final var foo = dishTypeRepo.findByAcronym(DishTypeAcronym.valueOf(acronym))
				.orElseThrow(() -> new NotFoundException("Cannot update an object that does not yet exist"));

		// apply update
		foo.updateData(expectedVersion, request.getShortDescription(), request.getLongDescription());

		return dishTypeRepo.save(foo);
	}

	/**
	 * the state of the dish type is not possible to change via the update or
	 * replace method and must be explicitly changed
	 *
	 * @param acronym
	 * @return
	 */
	@Override
	public DishType toogleState(final String acronym, final long expectedVersion) {
		throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
	}

	@Override
	public Optional<DishType> findByAcronym(final String acronym) {
		return dishTypeRepo.findByAcronym(DishTypeAcronym.valueOf(acronym));
	}
}
