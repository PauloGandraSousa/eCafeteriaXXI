package org.pagsousa.ecafeteriaxxi.dishmanagement.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Paulo Gandra Sousa 19/07/2023.
 *
 */
@Data
@Schema(description = "An allergen")
public class AllergenView {

	private String shortName;
	private String fullName;
}
