package org.pagsousa.ecafeteriaxxi.dishmanagement.api;

import org.pagsousa.ecafeteriaxxi.dishmanagement.application.CreateOrReplaceDishRequest;
import org.pagsousa.ecafeteriaxxi.dishmanagement.application.DishService;
import org.pagsousa.ecafeteriaxxi.dishmanagement.application.UpdateDishRequest;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.exceptions.NotFoundException;
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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Paulo Gandra Sousa 13/07/2023.
 *
 */
@Tag(name = "Dish management", description = "Endpoints for managing dishes")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dish")
public class DishResource extends AbstractResource {

	private final DishService service;
	private final DishViewMapper viewMapper;

	@Operation(summary = "Gets all dishes")
	@ApiResponse(description = "Success", responseCode = "200", content = { @Content(mediaType = "application/json",
			// Use the `array` property instead of `schema`
			array = @ArraySchema(schema = @Schema(implementation = DishView.class))) })
	@GetMapping
	public Iterable<DishView> findAll() {
		return viewMapper.toView(service.findAll());
	}

	@Operation(summary = "Gets a specific dish")
	@GetMapping(value = "/{id}")
	public ResponseEntity<DishView> findByUUID(
			@PathVariable("id") @Parameter(description = "The id of the dish to find") final String id) {
		final var dish = service.findById(id).orElseThrow(() -> new NotFoundException(DishType.class, id));
		return ResponseEntity.ok().eTag(Long.toString(dish.getVersion())).body(viewMapper.toView(dish));
	}

	@Operation(summary = "Creates a new dish")
	@PostMapping
	public ResponseEntity<DishView> create(@Valid @RequestBody final CreateOrReplaceDishRequest resource) {
		final var dish = service.create(resource);
		final var newUri = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(dish.identity().toString())
				.build().toUri();
		return ResponseEntity.created(newUri).eTag(Long.toString(dish.getVersion())).body(viewMapper.toView(dish));
	}

	@Operation(summary = "Fully replaces an existing dish.")
	@PutMapping(value = "/{id}")
	public ResponseEntity<DishView> upsert(final WebRequest request,
			@PathVariable("id") @Parameter(description = "The id of the dish to replace") final String id,
			@Valid @RequestBody final CreateOrReplaceDishRequest resource) {
		final var ifMatchValue = ensureIfMatchHeader(request);
		final var dish = service.replace(id, resource, getVersionFromIfMatchHeader(ifMatchValue));
		return ResponseEntity.ok().eTag(Long.toString(dish.getVersion())).body(viewMapper.toView(dish));
	}

	@Operation(summary = "Partially updates an existing dish")
	@PatchMapping(value = "/{id}")
	public ResponseEntity<DishView> partialUpdate(final WebRequest request,
			@PathVariable("id") @Parameter(description = "The id of the dish to update") final String id,
			@Valid @RequestBody final UpdateDishRequest resource) {
		final var ifMatchValue = ensureIfMatchHeader(request);
		final var dish = service.update(id, resource, getVersionFromIfMatchHeader(ifMatchValue));
		return ResponseEntity.ok().eTag(Long.toString(dish.getVersion())).body(viewMapper.toView(dish));
	}

	@Operation(summary = "Deletes an existing dish")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(final WebRequest request,
			@PathVariable("id") @Parameter(description = "The id of the dish to delete") final String id) {
		final var ifMatchValue = ensureIfMatchHeader(request);
		final var count = service.deleteById(id, getVersionFromIfMatchHeader(ifMatchValue));
		// TODO check if we can distinguish between a 404 and a 412
		return count == 1 ? ResponseEntity.noContent().build() : ResponseEntity.status(412).build();
	}

	@Operation(summary = "Toogles the state of an existing dish")
	@PostMapping("/{id}/state")
	public ResponseEntity<DishView> toogleState(final WebRequest request,
			@PathVariable("id") @Parameter(description = "The id of the dish to update") final String id,
			@Valid @RequestBody final EmptyRequest resource) {
		final var ifMatchValue = ensureIfMatchHeader(request);
		final var dish = service.toogleState(id, getVersionFromIfMatchHeader(ifMatchValue));
		return ResponseEntity.ok().eTag(Long.toString(dish.getVersion())).body(viewMapper.toView(dish));
	}
}
