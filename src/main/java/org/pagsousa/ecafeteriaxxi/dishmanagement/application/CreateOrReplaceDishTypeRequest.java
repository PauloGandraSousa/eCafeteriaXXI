package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * the DTO for creation or full update of a dishtype
 *
 * @author Paulo Gandra Sousa 06/07/2023.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrReplaceDishTypeRequest {
	@NotNull
	@NotBlank
	@Size(min = 1, max = 255)
	private String shortDescription;

	@Size(min = 1, max = 2048)
	private String longDescription;
}
