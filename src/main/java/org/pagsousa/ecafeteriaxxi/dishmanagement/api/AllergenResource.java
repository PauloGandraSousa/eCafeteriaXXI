package org.pagsousa.ecafeteriaxxi.dishmanagement.api;

import java.io.IOException;

import org.pagsousa.ecafeteriaxxi.dishmanagement.application.AllergenService;
import org.pagsousa.ecafeteriaxxi.dishmanagement.domain.model.Allergen;
import org.pagsousa.ecafeteriaxxi.exceptions.NotFoundException;
import org.pagsousa.ecafeteriaxxi.util.api.AbstractResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
@Tag(name = "Dish type management", description = "Endpoints for managing dish types")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/allergen")
public class AllergenResource extends AbstractResource {

	private final AllergenService service;
	private final AllergenViewMapper viewMapper;

	@Operation(summary = "Gets all allergens")
	@ApiResponse(description = "Success", responseCode = "200", content = { @Content(mediaType = "application/json",
			// Use the `array` property instead of `schema`
			array = @ArraySchema(schema = @Schema(implementation = DishTypeView.class))) })
	@GetMapping
	public Iterable<AllergenView> findAll() {
		return viewMapper.toView(service.findAll());
	}

	@Operation(summary = "Gets a specific allergen")
	@GetMapping(value = "/{name}")
	public ResponseEntity<AllergenView> findByName(
			@PathVariable("name") @Parameter(description = "The name of the allergen to find") final String name) {
		final var allergen = service.findByName(name).orElseThrow(() -> new NotFoundException(Allergen.class, name));
		return ResponseEntity.ok().eTag(Long.toString(allergen.getVersion())).body(viewMapper.toView(allergen));
	}

	@Operation(summary = "Gets the image of an allergen")
	@GetMapping(value = "/{name}/image", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImage(
			@PathVariable("name") @Parameter(description = "The name of the allergen to find") final String name) {
		final var allergen = service.findByName(name).orElseThrow(() -> new NotFoundException(Allergen.class, name));
		// TODO actually check the media type of the image to return and set the appropriate content-type header
		return allergen.image();
	}
}
