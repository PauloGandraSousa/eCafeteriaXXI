package org.pagsousa.ecafeteriaxxi.dishmanagement.api;

import javax.validation.Valid;

import org.pagsousa.ecafeteriaxxi.dishmanagement.application.CreateOrReplaceDishRequest;
import org.pagsousa.ecafeteriaxxi.dishmanagement.application.DishService;
import org.pagsousa.ecafeteriaxxi.dishmanagement.application.UpdateDishRequest;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.DishType;
import org.pagsousa.ecafeteriaxxi.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;
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
 * @author Paulo Gandra Sousa 13/07/2023.
 *
 */
@Tag(name = "Dish management", description = "Endpoints for managing dishes")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dish")
public class DishResource {

	private final DishService service;
	private final DishViewMapper viewMapper;

	private Long getVersionFromIfMatchHeader(final String ifMatchHeader) {
		if (ifMatchHeader.startsWith("\"")) {
			return Long.parseLong(ifMatchHeader.substring(1, ifMatchHeader.length() - 1));
		}
		return Long.parseLong(ifMatchHeader);
	}

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
		final var d = service.findById(id).orElseThrow(() -> new NotFoundException(DishType.class, id));

		return ResponseEntity.ok().eTag(Long.toString(d.getVersion())).body(viewMapper.toView(d));
	}

	@Operation(summary = "Creates a new dish")
	@PostMapping
	public ResponseEntity<DishView> create(@Valid @RequestBody final CreateOrReplaceDishRequest resource) {
		final var d = service.create(resource);
		final var dishUri = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(d.identity().toString())
				.build().toUri();
		return ResponseEntity.created(dishUri).eTag(Long.toString(d.getVersion())).body(viewMapper.toView(d));
	}

	@Operation(summary = "Fully replaces an existing dish.")
	@PutMapping(value = "/{id}")
	public ResponseEntity<DishView> upsert(final WebRequest request,
			@PathVariable("id") @Parameter(description = "The id of the dish to replace") final String id,
			@Valid @RequestBody final CreateOrReplaceDishRequest resource) {
		final var ifMatchValue = request.getHeader("If-Match");
		if (ifMatchValue == null || ifMatchValue.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"You must issue a conditional PUT using 'if-match'");
		}
		// if-match header was sent, so we are in UPDATE mode
		final var d = service.replace(id, resource, getVersionFromIfMatchHeader(ifMatchValue));
		return ResponseEntity.ok().eTag(Long.toString(d.getVersion())).body(viewMapper.toView(d));
	}

	@Operation(summary = "Partially updates an existing dish")
	@PatchMapping(value = "/{id}")
	public ResponseEntity<DishView> partialUpdate(final WebRequest request,
			@PathVariable("id") @Parameter(description = "The id of the dish to update") final String id,
			@Valid @RequestBody final UpdateDishRequest resource) {
		final var ifMatchValue = request.getHeader("If-Match");
		if (ifMatchValue == null || ifMatchValue.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"You must issue a conditional PATCH using 'if-match'");
		}

		final var d = service.update(id, resource, getVersionFromIfMatchHeader(ifMatchValue));
		return ResponseEntity.ok().eTag(Long.toString(d.getVersion())).body(viewMapper.toView(d));
	}

	@Operation(summary = "Deletes an existing dish")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(final WebRequest request,
			@PathVariable("id") @Parameter(description = "The id of the dish to delete") final String id) {
		final var ifMatchValue = request.getHeader("If-Match");
		if (ifMatchValue == null || ifMatchValue.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"You must issue a conditional DELETE using 'if-match'");
		}
		final var count = service.deleteById(id, getVersionFromIfMatchHeader(ifMatchValue));

		// TODO check if we can distinguish between a 404 and a 412
		return count == 1 ? ResponseEntity.noContent().build() : ResponseEntity.status(412).build();
	}
}
