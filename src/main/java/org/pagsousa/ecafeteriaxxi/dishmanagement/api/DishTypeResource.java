package org.pagsousa.ecafeteriaxxi.dishmanagement.api;

import java.net.URISyntaxException;

import javax.validation.Valid;

import org.pagsousa.ecafeteriaxxi.dishmanagement.application.CreateOrReplaceDishTypeRequest;
import org.pagsousa.ecafeteriaxxi.dishmanagement.application.DishTypeService;
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
 * @author Paulo Gandra Sousa 06/07/2023.
 *
 */
@Tag(name = "Dish management", description = "Endpoints for managing dishes and dish types")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dishtype")
public class DishTypeResource {

	private final DishTypeService service;
	private final DishTypeViewMapper viewMapper;

	private Long getVersionFromIfMatchHeader(final String ifMatchHeader) {
		if (ifMatchHeader.startsWith("\"")) {
			return Long.parseLong(ifMatchHeader.substring(1, ifMatchHeader.length() - 1));
		}
		return Long.parseLong(ifMatchHeader);
	}

	@Operation(summary = "Gets all dish types")
	@ApiResponse(description = "Success", responseCode = "200", content = { @Content(mediaType = "application/json",
			// Use the `array` property instead of `schema`
			array = @ArraySchema(schema = @Schema(implementation = DishTypeView.class))) })
	@GetMapping
	public Iterable<DishTypeView> findAll() {
		return viewMapper.toView(service.findAll());
	}

	@Operation(summary = "Gets a specific dish type")
	@GetMapping(value = "/{acronym}")
	public ResponseEntity<DishTypeView> findByAcronym(
			@PathVariable("acronym") @Parameter(description = "The acronym of the dish type to find") final String acronym) {
		final var foo = service.findByAcronym(acronym)
				.orElseThrow(() -> new NotFoundException(DishType.class, acronym));

		return ResponseEntity.ok().eTag(Long.toString(foo.getVersion())).body(viewMapper.toView(foo));
	}

	/**
	 * PUT is used either to fully replace an existing resource or create a new
	 * resource, i.e., UPSERT
	 *
	 * @param request
	 * @param id
	 * @param resource
	 * @return
	 * @throws URISyntaxException
	 */
	//
	@Operation(summary = "Fully replaces an existing dish type or creates a new one if the specified acronym does not exist.")
	@PutMapping(value = "/{acronym}")
	public ResponseEntity<DishTypeView> upsert(final WebRequest request,
			@PathVariable("acronym") @Parameter(description = "The acronym of the dish type to replace/create") final String acronym,
			@Valid @RequestBody final CreateOrReplaceDishTypeRequest resource) {
		final var ifMatchValue = request.getHeader("If-Match");
		if (ifMatchValue == null || ifMatchValue.isEmpty()) {
			// no if-match header was sent, so we are in INSERT mode
			final var dt = service.create(acronym, resource);
			final var newFooUri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
			return ResponseEntity.created(newFooUri).eTag(Long.toString(dt.getVersion())).body(viewMapper.toView(dt));
		}
		// if-match header was sent, so we are in UPDATE mode
		final var dt = service.replace(acronym, resource, getVersionFromIfMatchHeader(ifMatchValue));
		return ResponseEntity.ok().eTag(Long.toString(dt.getVersion())).body(viewMapper.toView(dt));
	}

	@Operation(summary = "Partially updates an existing dish type")
	@PatchMapping(value = "/{acronym}")
	public ResponseEntity<DishTypeView> partialUpdate(final WebRequest request,
			@PathVariable("acronym") @Parameter(description = "The acronym of the dish type to update") final String acronym,
			@Valid @RequestBody final CreateOrReplaceDishTypeRequest resource) {
		final var ifMatchValue = request.getHeader("If-Match");
		if (ifMatchValue == null || ifMatchValue.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"You must issue a conditional PATCH using 'if-match'");
		}

		final var foo = service.update(acronym, resource, getVersionFromIfMatchHeader(ifMatchValue));
		return ResponseEntity.ok().eTag(Long.toString(foo.getVersion())).body(viewMapper.toView(foo));
	}

	@Operation(summary = "Deletes an existing dish type")
	@DeleteMapping(value = "/{acronym}")
	public ResponseEntity<String> delete(final WebRequest request,
			@PathVariable("acronym") @Parameter(description = "The acronym of the dish type to delete") final String acronym) {
		final var ifMatchValue = request.getHeader("If-Match");
		if (ifMatchValue == null || ifMatchValue.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"You must issue a conditional DELETE using 'if-match'");
		}
		final var count = service.deleteByAcronym(acronym, getVersionFromIfMatchHeader(ifMatchValue));

		// TODO check if we can distinguish between a 404 and a 412
		return count == 1 ? ResponseEntity.noContent().build() : ResponseEntity.status(412).build();
	}

	@Operation(summary = "Toogles the state of an existing dish type")
	@PostMapping("/{acronym}/state")
	public ResponseEntity<DishTypeView> toogleState(final WebRequest request,
			@PathVariable("acronym") @Parameter(description = "The acronym of the dish type to update") final String acronym,
			@Valid @RequestBody final ToogleStateRequest resource) {
		final var ifMatchValue = request.getHeader("If-Match");
		if (ifMatchValue == null || ifMatchValue.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"You must issue a conditional POST using 'if-match'");
		}
		final var dt = service.toogleState(acronym, getVersionFromIfMatchHeader(ifMatchValue));
		return ResponseEntity.ok().eTag(Long.toString(dt.getVersion())).body(viewMapper.toView(dt));
	}
}