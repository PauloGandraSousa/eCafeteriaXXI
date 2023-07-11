package org.pagsousa.ecafeteriaxxi.dishmanagement.application;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * the DTO for creation or full update of a dishtype
 *
 * @author Paulo Gandra Sousa 06/07/2023.
 *
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrReplaceDishTypeRequest {
	@NotNull
	@NotBlank
	@Size(min = 1, max = 255)
	private String shortDescription;

	@Size(min = 1, max = 1024)
	private String longDescription;
}
