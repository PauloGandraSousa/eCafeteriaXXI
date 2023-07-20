package org.pagsousa.ecafeteriaxxi.mealmanagement.application;

import java.time.LocalDate;

import org.pagsousa.ecafeteriaxxi.exceptions.NotFoundException;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.repositories.MealRepository;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.services.MealPlanningService;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.Cafeteria;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.CafeteriaName;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.repositories.CafeteriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

/**
 * the service (use case controller) for the meal plan resource
 *
 * @author Paulo Gandra Sousa 17/07/2023
 */
@Service
@AllArgsConstructor
public class MealPlanServiceImpl implements MealPlanService {

	private final MealPlanningService planner;
	private final MealRepository mealRepo;
	private final CafeteriaRepository cafeRepo;

	@Override
	@Transactional
	public Iterable<Meal> plan(final MealPlanRequest request) {

		final var from = LocalDate.parse(request.getFrom());
		final var to = LocalDate.parse(request.getTo());
		final var cafe = cafeRepo.findByName(CafeteriaName.valueOf(request.getCafeteriaName()))
				.orElseThrow(() -> new NotFoundException(Cafeteria.class, request.getCafeteriaName()));

		final var menu = planner.plan(cafe, from, to);

		if (request.isCreateMeals()) {
			for (final Meal m : menu) {
				mealRepo.save(m);
			}
		}
		return menu;
	}
}
