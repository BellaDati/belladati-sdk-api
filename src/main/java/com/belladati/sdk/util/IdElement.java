package com.belladati.sdk.util;

/**
 * An element with a unique ID. This can be the element's own ID or the ID of a
 * resource it points to.
 * 
 * 
 */
public interface IdElement {

	/**
	 * Returns the ID of this element, or the ID of the item it represents.
	 * 
	 * @return the ID of this element, or the ID of the item it represents
	 */
	String getId();
}
