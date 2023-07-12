package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import java.util.Optional;

import org.hibernate.StaleObjectStateException;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishTypeAcronym;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishTypeRepository;
import org.pagsousa.ecafeteriaxxi.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private final CreateOrReplaceDishTypeMapper createOrReplaceMapper;
	private final PatchDishTypeMapper patchMapper;

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
		final var dt = createOrReplaceMapper.create(acronym, request);
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
	public DishType update(final String acronym, final CreateOrReplaceDishTypeRequest request,
			final long expectedVersion) {
		final var dt = fetchCheckingVersion(acronym, expectedVersion);

		// update data - partial replace
		patchMapper.patch(dt, request);

		// in the meantime some other user might have changed this object on the
		// database, so concurrency control will still be applied when we try to save
		// this updated object
		return dishTypeRepo.save(dt);
	}

	/**
	 * fetches the dish type from the repository, checking if it is currently in the
	 * expected version or if it has been updated by another user.
	 *
	 * @param acronym
	 * @param expectedVersion
	 * @return
	 * @throws NotFoundException
	 * @throws StaleObjectStateException
	 */
	private DishType fetchCheckingVersion(final String acronym, final long expectedVersion) {
		// first let's check if the object exists so we don't create a new object
		final var dt = findByAcronym(acronym)
				.orElseThrow(() -> new NotFoundException("Cannot update an object that does not yet exist"));

		// check current version so we don't execute unnecessary operations
		if (dt.getVersion() != expectedVersion) {
			throw new StaleObjectStateException("Object was already modified by another user", acronym);
		}
		return dt;
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
		final var dt = fetchCheckingVersion(acronym, expectedVersion);

		// update data - full replace
		createOrReplaceMapper.replace(dt, request);

		// the save() method in the repository will check again at the database level if
		// the object was modified by another thread
		return dishTypeRepo.save(dt);
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
		final var dt = fetchCheckingVersion(acronym, expectedVersion);

		// update data - full replace
		dt.toogleState();

		// the save() method in the repository will check again at the database level if
		// the object was modified by another thread
		return dishTypeRepo.save(dt);
	}

	@Override
	public Optional<DishType> findByAcronym(final String acronym) {
		return dishTypeRepo.findByAcronym(DishTypeAcronym.valueOf(acronym));
	}

	@Transactional
	@Override
	public int deleteByAcronym(final String acronym, final long expectedVersion) {
		return dishTypeRepo.deleteByAcronymIfMatch(DishTypeAcronym.valueOf(acronym), expectedVersion);
	}
}
