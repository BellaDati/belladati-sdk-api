package com.belladati.sdk.view;

import java.util.Collection;

import com.belladati.sdk.filter.Filter;
import com.belladati.sdk.util.Resource;

/**
 * A BellaDati view in a report or dashboard. Call
 * {@link #loadContent(Filter...)} to get the view's displayable content.
 * 
 * @author Chris Hennigfeld
 */
public interface View extends Resource {

	/**
	 * Returns the type of this view.
	 * 
	 * @return the type of this view
	 */
	ViewType getType();

	/**
	 * Loads the content of this view. The type of this view determines what
	 * content to expect.
	 * 
	 * @param filters optional filters to use when loading the view
	 * @return the content of this view
	 */
	Object loadContent(Filter<?>... filters);

	/**
	 * Loads the content of this view. The type of this view determines what
	 * content to expect.
	 * 
	 * @param filters filters to use when loading the view
	 * @return the content of this view
	 */
	Object loadContent(Collection<Filter<?>> filters);
}
