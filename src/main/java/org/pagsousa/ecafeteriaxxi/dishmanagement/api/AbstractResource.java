package org.pagsousa.ecafeteriaxxi.dishmanagement.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Paulo Gandra Sousa 14/07/2023.
 *
 */
public class AbstractResource {

	public AbstractResource() {
		// empty
	}

	protected Long getVersionFromIfMatchHeader(final String ifMatchHeader) {
		if (ifMatchHeader.startsWith("\"")) {
			return Long.parseLong(ifMatchHeader.substring(1, ifMatchHeader.length() - 1));
		}
		return Long.parseLong(ifMatchHeader);
	}

	protected String ensureIfMatchHeader(final WebRequest request) {
		final var ifMatchValue = request.getHeader("If-Match");
		if (ifMatchValue == null || ifMatchValue.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"You must issue a conditional request using 'if-match'");
		}
		return ifMatchValue;
	}

}