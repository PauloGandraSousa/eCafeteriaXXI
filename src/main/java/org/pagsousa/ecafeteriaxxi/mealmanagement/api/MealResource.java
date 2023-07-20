package org.pagsousa.ecafeteriaxxi.mealmanagement.api;

import javax.validation.Valid;

import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.exceptions.NotFoundException;
import org.pagsousa.ecafeteriaxxi.mealmanagement.application.CreateMealRequest;
import org.pagsousa.ecafeteriaxxi.mealmanagement.application.MealService;
import org.pagsousa.ecafeteriaxxi.mealmanagement.application.ReplaceMealRequest;
import org.pagsousa.ecafeteriaxxi.mealmanagement.application.UpdateMealRequest;
import org.pagsousa.ecafeteriaxxi.util.api.AbstractResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Paulo Gandra Sousa 17/07/2023.
 *
 */
@Tag(name = "Meal management", description = "Endpoints for managing meals")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meal")
public class MealResource extends AbstractResource {

	private final MealService service;
	private final MealViewMapper viewMapper;

	@Operation(summary = "Gets all meals")
	@ApiResponse(description = "Success", responseCode = "200", content = { @Content(mediaType = "application/json",
			// Use the `array` property instead of `schema`
			array = @ArraySchema(schema = @Schema(implementation = MealView.class))) })
	@GetMapping
	public Iterable<MealView> findAll() {
		return viewMapper.toView(service.findAll());
	}

	@Operation(summary = "Gets a specific meal")
	@GetMapping(value = "/{id}")
	public ResponseEntity<MealView> findById(
			@PathVariable("id") @Parameter(description = "The id of the meal to find") final String id) {
		final var meal = service.findById(id).orElseThrow(() -> new NotFoundException(DishType.class, id));
		return ResponseEntity.ok().eTag(Long.toString(meal.getVersion())).body(viewMapper.toView(meal));
	}

	@Operation(summary = "Creates a new meal")
	@PostMapping
	public ResponseEntity<MealView> create(@Valid @RequestBody final CreateMealRequest request) {
		final var meal = service.create(request);
		final var newUri = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(meal.identity().toString()).build()
				.toUri();
		return ResponseEntity.created(newUri).eTag(Long.toString(meal.getVersion())).body(viewMapper.toView(meal));
	}

	@Operation(summary = "Fully replaces an existing meal.")
	@PutMapping(value = "/{id}")
	public ResponseEntity<MealView> upsert(final WebRequest request,
			@PathVariable("id") @Parameter(description = "The id of the meal to replace") final String id,
			@Valid @RequestBody final ReplaceMealRequest resource) {
		final var ifMatchValue = ensureIfMatchHeader(request);
		final var meal = service.replace(id, resource, getVersionFromIfMatchHeader(ifMatchValue));
		return ResponseEntity.ok().eTag(Long.toString(meal.getVersion())).body(viewMapper.toView(meal));
	}

	@Operation(summary = "Partially updates an existing meal")
	@PatchMapping(value = "/{id}")
	public ResponseEntity<MealView> partialUpdate(final WebRequest request,
			@PathVariable("id") @Parameter(description = "The id of the meal to update") final String id,
			@Valid @RequestBody final UpdateMealRequest resource) {
		final var ifMatchValue = ensureIfMatchHeader(request);
		final var meal = service.update(id, resource, getVersionFromIfMatchHeader(ifMatchValue));
		return ResponseEntity.ok().eTag(Long.toString(meal.getVersion())).body(viewMapper.toView(meal));
	}

	@Operation(summary = "Deletes an existing meal")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(final WebRequest request,
			@PathVariable("id") @Parameter(description = "The id of the meal to delete") final String id) {
		final var ifMatchValue = ensureIfMatchHeader(request);
		final var count = service.deleteById(id, getVersionFromIfMatchHeader(ifMatchValue));
		// TODO check if we can distinguish between a 404 and a 412
		return count == 1 ? ResponseEntity.noContent().build() : ResponseEntity.status(412).build();
	}
}
