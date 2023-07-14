package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.StaleObjectStateException;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishRepository;
import org.pagsousa.ecafeteriaxxi.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
 * The typical architecture of a REST resource uses a single Service to handle
 * all the use cases. Alternatively we could use a separate use case controller
 * class for each operation.
 *
 * @author Paulo Gandra Sousa 14/07/2023.
 *
 */
@Service
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

	private final DishRepository dishRepo;
	private final CreateOrReplaceDishMapper createOrReplaceMapper;
	private final PatchDishMapper patchMapper;

	/**
	 *
	 * @return
	 */
	@Override
	public Iterable<Dish> findAll() {
		return dishRepo.findAll();
	}

	/**
	 *
	 * @param acronym
	 * @param request
	 * @return
	 */
	@Override
	public Dish create(final CreateOrReplaceDishRequest request) {
		final var d = createOrReplaceMapper.create(request);
		return dishRepo.save(d);
	}

	@Override
	public Optional<Dish> findById(final String uuid) {
		return dishRepo.findById(UUID.fromString(uuid));
	}

	@Override
	public Dish replace(final String id, final CreateOrReplaceDishRequest request, final long expectedVersion) {
		final var dt = fetchCheckingVersion(id, expectedVersion);

		// update data - full replace
		createOrReplaceMapper.replace(dt, request);

		// in the meantime some other user might have changed this object on the
		// database, so concurrency control will still be applied when we try to save
		// this updated object
		return dishRepo.save(dt);
	}

	/**
	 * fetches the dish from the repository, checking if it is currently in the
	 * expected version or if it has been updated by another user.
	 *
	 * @param acronym
	 * @param expectedVersion
	 * @return
	 * @throws NotFoundException
	 * @throws StaleObjectStateException
	 */
	private Dish fetchCheckingVersion(final String id, final long expectedVersion) {
		// first let's check if the object exists so we don't create a new object
		final var d = findById(id)
				.orElseThrow(() -> new NotFoundException("Cannot update an object that does not yet exist"));

		// check current version so we don't execute unnecessary operations
		if (d.getVersion() != expectedVersion) {
			throw new StaleObjectStateException("Object was already modified by another user", id);
		}
		return d;
	}

	@Override
	public Dish update(final String id, final UpdateDishRequest request, final long expectedVersion) {
		final var dt = fetchCheckingVersion(id, expectedVersion);

		// update data - partial replace
		patchMapper.patch(dt, request);

		// in the meantime some other user might have changed this object on the
		// database, so concurrency control will still be applied when we try to save
		// this updated object
		return dishRepo.save(dt);
	}

	@Override
	@Transactional
	public int deleteById(final String id, final long expectedVersion) {
		return dishRepo.deleteByIdIfMatch(UUID.fromString(id), expectedVersion);
	}

	@Override
	public Dish toogleState(final String id, final long expectedVersion) {
		final var d = fetchCheckingVersion(id, expectedVersion);

		// update data - full replace
		d.toogleState();

		// the save() method in the repository will check again at the database level if
		// the object was modified by another thread
		return dishRepo.save(d);
	}
}
