package com.belladati.sdk.util;

/**
 * An information object representing a resource from the BellaDati API. Call
 * {@link #loadDetails()} to load the full resource from the server.
 * 
 * @author Chris Hennigfeld
 */
public interface ResourceInfo<T extends Resource> extends IdElement {

	/**
	 * Returns the ID of the resource represented by this info object.
	 * 
	 * @return the ID of the resource represented by this info object
	 */
	String getId();

	/**
	 * Returns the name of the resource represented by this info object.
	 * 
	 * @return the name of the resource represented by this info object
	 */
	String getName();

	/**
	 * Loads the full resource from the server.
	 * 
	 * @return the full resource represented by this info object
	 */
	T loadDetails();
}
