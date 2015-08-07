package com.belladati.sdk.view;

import java.util.Collection;

import com.belladati.sdk.filter.Filter;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * A view displaying JSON content.
 * 
 * @author Chris Hennigfeld
 */
public interface JsonView extends View {

	/**
	 * Loads the JSON representation of this view.
	 * 
	 * @param filters optional filters to use when loading the view
	 * @return the JSON representation of this view
	 */
	JsonNode loadContent(Filter<?>... filters);

	/**
	 * Loads the JSON representation of this view.
	 * 
	 * @param filters filters to use when loading the view
	 * @return the JSON representation of this view
	 */
	JsonNode loadContent(Collection<Filter<?>> filters);
}
