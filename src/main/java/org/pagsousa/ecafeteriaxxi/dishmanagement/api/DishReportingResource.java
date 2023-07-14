package org.pagsousa.ecafeteriaxxi.dishmanagement.api;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishReportingRepository;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishesPerCaloricCategory;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.repositories.DishesPerDishType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Paulo Gandra Sousa 13/07/2023.
 *
 */
@Tag(name = "Dish reporting", description = "Endpoints for reporting dishes")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reporting/dish")
public class DishReportingResource extends AbstractResource {

	// since this is just a reporting resource, there is no business logic, we are
	// calling the repository directly from the REST controller
	private final DishReportingRepository repo;
	private final DishViewMapper viewMapper;

	@Operation(summary = "Gets high calory count dishes")
	@GetMapping("/high-calories-dishes")
	public Iterable<DishView> reportHighCaloriesDishes() {
		return viewMapper.toView(repo.reportHighCaloriesDishes());
	}

	@Operation(summary = "Gets dishes per dish type")
	@GetMapping("/dishes-per-dishtype")
	Iterable<DishesPerDishType> reportDishesPerDishType() {
		return repo.reportDishesPerDishType();
	}

	@Operation(summary = "Gets dishes per caloric category")
	@GetMapping("/dishes-per-caloric-category")
	Iterable<DishesPerCaloricCategory> reportDishesPerCaloricCategory() {
		return repo.reportDishesPerCaloricCategory();
	}

	@Operation(summary = "Gets dishes per caloric category (as tuples)")
	@GetMapping("/dishes-per-caloric-category-tuples")
	Iterable<Object[]> reportDishesPerCaloricCategoryAsTuples() {
		return repo.reportDishesPerCaloricCategoryAsTuples();
	}
}
