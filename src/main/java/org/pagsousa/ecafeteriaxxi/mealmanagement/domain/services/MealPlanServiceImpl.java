package org.pagsousa.ecafeteriaxxi.mealmanagement.domain.services;

import java.time.LocalDate;

import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;
import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.repositories.MealRepository;
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

	@Override
	@Transactional
	public Iterable<Meal> plan(final LocalDate from, final LocalDate to, final boolean createMeals) {
		final var menu = planner.plan(from, to);

		if (createMeals) {
			for (final Meal m : menu) {
				mealRepo.save(m);
			}
		}
		return menu;
	}
}
