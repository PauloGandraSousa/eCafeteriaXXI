package org.pagsousa.ecafeteriaxxi.dishmanagement.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *
 * @author Paulo Gandra Sousa 13/07/2023.
 *
 */
@Data
@Schema(description = "A dish")
public class DishView {
	private String id;

	private String name;

	private String shortDescription;

	/**
	 * format: dd.d {CURRENCY}
	 *
	 * example: 3.99 EUR
	 */
	private String price;

	private String longDescription;

	private boolean active;
}
