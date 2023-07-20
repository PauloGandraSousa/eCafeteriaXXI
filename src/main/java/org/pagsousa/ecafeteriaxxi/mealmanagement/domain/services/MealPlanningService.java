package org.pagsousa.ecafeteriaxxi.mealmanagement.domain.services;

import java.time.LocalDate;

import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.Cafeteria;

/**
 * The planning strategy/algorithm.
 *
 *
 * @author Paulo Gandra Sousa 17/07/2023
 */
public interface MealPlanningService {

	Iterable<Meal> plan(Cafeteria cafe, LocalDate from, LocalDate to);
}
