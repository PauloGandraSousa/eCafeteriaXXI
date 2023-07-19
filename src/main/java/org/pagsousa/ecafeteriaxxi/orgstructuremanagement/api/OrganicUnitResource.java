package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.api;

import org.pagsousa.ecafeteriaxxi.exceptions.NotFoundException;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.application.OrganicUnitService;
import org.pagsousa.ecafeteriaxxi.orgstructuremanagement.domain.model.OrganicUnit;
import org.pagsousa.ecafeteriaxxi.util.api.AbstractResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 * @author Paulo Gandra Sousa 19/07/2023.
 *
 */
@Tag(name = "Organizational structure management", description = "Endpoints for managing the organizational structure")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orgunit")
public class OrganicUnitResource extends AbstractResource {

	private final OrganicUnitService service;
	private final OrganicUnitViewMapper ouViewMapper;
	private final CafeteriaViewMapper cafeViewMapper;

	@Operation(summary = "Gets all organic units")
	@ApiResponse(description = "Success", responseCode = "200", content = { @Content(mediaType = "application/json",
			// Use the `array` property instead of `schema`
			array = @ArraySchema(schema = @Schema(implementation = OrganicUnitView.class))) })
	@GetMapping
	public Iterable<OrganicUnitView> findAll() {
		return ouViewMapper.toView(service.findAll());
	}

	@Operation(summary = "Gets a specific organic unit")
	@GetMapping(value = "/{acronym}")
	public ResponseEntity<OrganicUnitView> findByAcronym(
			@PathVariable("acronym") @Parameter(description = "The acronym of the organic unit to find") final String acronym) {
		final var dt = service.findByAcronym(acronym)
				.orElseThrow(() -> new NotFoundException(OrganicUnit.class, acronym));

		return ResponseEntity.ok().eTag(Long.toString(dt.getVersion())).body(ouViewMapper.toView(dt));
	}

	@Operation(summary = "Gets all cafeterias of an organic unit")
	@ApiResponse(description = "Success", responseCode = "200", content = { @Content(mediaType = "application/json",
			// Use the `array` property instead of `schema`
			array = @ArraySchema(schema = @Schema(implementation = CafeteriaView.class))) })
	@GetMapping(value = "/{acronym}/cafe")
	public Iterable<CafeteriaView> findCafeteriasOfOrgUnit(
			@PathVariable("acronym") @Parameter(description = "The acronym of the organic unit to find") final String acronym) {
		return cafeViewMapper.toView(service.findCafeteriasByOrgUnit(acronym));
	}

	@Operation(summary = "Gets a specific cafeteria of an organic unit")
	@GetMapping(value = "/{acronym}/cafe/{name}")
	public ResponseEntity<CafeteriaView> findCafeteriaOfOrgUnit(
			@PathVariable("acronym") @Parameter(description = "The acronym of the organic unit to find") final String acronym,
			@PathVariable("name") @Parameter(description = "The name of the cafeteria") final String cafeName) {
		final var dt = service.findCafeteriaOfOrgUnit(acronym, cafeName)
				.orElseThrow(() -> new NotFoundException(OrganicUnit.class, acronym));

		return ResponseEntity.ok().eTag(Long.toString(dt.getVersion())).body(cafeViewMapper.toView(dt));
	}

}
