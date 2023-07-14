package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import java.util.Optional;
import java.util.UUID;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Dish;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishRepository;
import org.springframework.stereotype.Service;

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
	private final CreateDishMapper createMapper;

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
	public Dish create(final CreateDishRequest request) {
		final var d = createMapper.create(request);
		return dishRepo.save(d);
	}

	@Override
	public Optional<Dish> findById(final String uuid) {
		return dishRepo.findById(UUID.fromString(uuid));
	}
}
