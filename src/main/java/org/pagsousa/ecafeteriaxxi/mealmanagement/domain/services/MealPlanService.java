package org.pagsousa.ecafeteriaxxi.mealmanagement.domain.services;

import java.time.LocalDate;

import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;

/**
 * the service (use case controller) for the meal plan resource
 *
 * @author Paulo Gandra Sousa 17/07/2023
 */
public interface MealPlanService {

	Iterable<Meal> plan(LocalDate from, LocalDate to, boolean createMeals);
}
