package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * the DTO for partial updates
 *
 * @author Paulo Gandra Sousa 06/07/2023.
 *
 */
@Data
@AllArgsConstructor
public class UpdateDishTypeRequest {
	@Size(min = 1, max = 255)
	private String shortDescription;

	@Size(min = 1, max = 1024)
	private String longDescription;
}
