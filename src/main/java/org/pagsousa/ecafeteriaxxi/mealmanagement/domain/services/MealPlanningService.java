package org.pagsousa.ecafeteriaxxi.mealmanagement.domain.services;

import java.time.LocalDate;

import org.pagsousa.ecafeteriaxxi.mealmanagement.domain.model.Meal;

/**
 *
 * @author Paulo Gandra Sousa 17/07/2023
 */
public interface MealPlanningService {

	Iterable<Meal> plan(LocalDate from, LocalDate to);
}
