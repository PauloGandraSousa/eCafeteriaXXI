package org.pagsousa.ecafeteriaxxi.mealmanagement.application;

import java.util.Optional;

import org.hibernate.StaleObjectStateException;
import org.pagsousa.ecafeteriaxxi.exceptions.NotFoundException;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.repositories.MealRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

/**
 * The typical architecture of a REST resource uses a single Service to handle
 * all the use cases. Alternatively we could use a separate use case controller
 * class for each operation.
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 *
 */
@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

	private final MealRepository mealRepo;
	private final CreateOrReplaceMealMapper createOrReplaceMapper;
	private final PatchMealMapper patchMapper;

	/**
	 *
	 * @return
	 */
	@Override
	public Iterable<Meal> findAll() {
		return mealRepo.findAll();
	}

	/**
	 *
	 * @param acronym
	 * @param request
	 * @return
	 */
	@Override
	public Meal create(final CreateMealRequest request) {
		final var d = createOrReplaceMapper.create(request);
		return mealRepo.save(d);
	}

	@Override
	public Optional<Meal> findById(final String uuid) {
		return mealRepo.findById(Long.parseLong(uuid));
	}

	@Override
	public Meal replace(final String id, final ReplaceMealRequest request, final long expectedVersion) {
		final var dt = fetchCheckingVersion(id, expectedVersion);

		// update data - full replace
		createOrReplaceMapper.replace(dt, request);

		// in the meantime some other user might have changed this object on the
		// database, so concurrency control will still be applied when we try to save
		// this updated object
		return mealRepo.save(dt);
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
	private Meal fetchCheckingVersion(final String id, final long expectedVersion) {
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
	public Meal update(final String id, final UpdateMealRequest request, final long expectedVersion) {
		final var dt = fetchCheckingVersion(id, expectedVersion);

		// update data - partial replace
		patchMapper.patch(dt, request);

		// in the meantime some other user might have changed this object on the
		// database, so concurrency control will still be applied when we try to save
		// this updated object
		return mealRepo.save(dt);
	}

	@Override
	@Transactional
	public int deleteById(final String id, final long expectedVersion) {
		return mealRepo.deleteByIdIfMatch(Long.parseLong(id), expectedVersion);
	}
}
