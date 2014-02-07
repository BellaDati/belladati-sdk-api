package com.belladati.sdk.util;

/**
 * A unique resource from the BellaDati API.
 * 
 * @author Chris Hennigfeld
 */
public interface Resource extends IdElement {

	/**
	 * Returns the ID of this resource.
	 * 
	 * @return the ID of this resource
	 */
	String getId();

	/**
	 * Returns the name of this resource.
	 * 
	 * @return the name of this resource
	 */
	String getName();
}
