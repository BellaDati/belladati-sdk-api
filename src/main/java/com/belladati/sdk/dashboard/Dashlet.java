package com.belladati.sdk.dashboard;

import com.belladati.sdk.view.View;

/**
 * Represents a single dashlet in a BellaDati dashboard. Refer to
 * {@link #getType()} to find out what type of content it contains.
 * 
 * @author Chris Hennigfeld
 */
public interface Dashlet {

	/**
	 * Types of dashlets. The type of a dashlet indicates what content it
	 * contains.
	 * 
	 * @author Chris Hennigfeld
	 */
	public enum Type {
		/**
		 * The dashlet contains a view. Its content is an instance of
		 * {@link View}.
		 */
		VIEW,
		/**
		 * The dashlet contains text. Its content is a String that may contain
		 * HTML markup.
		 */
		TEXT
	}

	/**
	 * Returns the name of this dashlet.
	 * 
	 * @return the name of this dashlet
	 */
	String getName();

	/**
	 * Returns the type of this dashlet.
	 * 
	 * @return the type of this dashlet
	 */
	Type getType();

	/**
	 * Returns the content resource held by this dashlet. The type of content
	 * depends on this dashlet's type as returned by {@link #getType()}. To work
	 * with the content, cast it to the appropriate type.
	 * 
	 * @return the content resource held by this dashlet
	 */
	Object getContent();
}
