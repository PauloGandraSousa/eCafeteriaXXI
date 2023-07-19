package org.pagsousa.ecafeteriaxxi.orgstructuremanagement.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Paulo Gandra Sousa 19/07/2023.
 *
 */
@Data
@Schema(description = "An organic unit")
public class OrganicUnitView {

	private String acronym;
	private String name;
	private String description;
}
