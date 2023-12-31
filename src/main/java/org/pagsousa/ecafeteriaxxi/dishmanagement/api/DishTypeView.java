package org.pagsousa.ecafeteriaxxi.dishmanagement.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Paulo Gandra Sousa 06/07/2023.
 *
 */
@Data
@Schema(description = "A dish type")
public class DishTypeView {
	private String acronym;

	private String shortDescription;

	private String longDescription;

	private boolean active;
}
