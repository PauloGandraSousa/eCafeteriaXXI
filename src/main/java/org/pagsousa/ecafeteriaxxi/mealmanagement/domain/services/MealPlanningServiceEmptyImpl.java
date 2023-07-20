package org.pagsousa.ecafeteriaxxi.mealmanagement.domain.services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.Cafeteria;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * empty, just a mock, meal planner
 *
 * @author Paulo Gandra Sousa 17/07/2023
 */
@Service
@AllArgsConstructor
@Profile("ecafeteriaxxi.MealPlanner.Empty")
public class MealPlanningServiceEmptyImpl implements MealPlanningService {

	@Override
	public Iterable<Meal> plan(final Cafeteria cafe, final LocalDate from, final LocalDate to) {
		return new ArrayList<>();
	}
}
