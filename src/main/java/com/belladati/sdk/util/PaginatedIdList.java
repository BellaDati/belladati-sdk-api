package com.belladati.sdk.util;

/**
 * A paginated list containing elements with an ID. Provides additional methods
 * to easily find elements with a given ID in the list.
 * 
 * 
 */
public interface PaginatedIdList<T extends IdElement> extends PaginatedList<T> {

	/**
	 * Returns true if an element with the given ID has been loaded in
	 * this list. Note that this method returns false if such an
	 * element exists but on a page that hasn't yet been loaded from the server.
	 * 
	 * @param id ID of the element to look for
	 * @return true if an element with the given ID has been loaded in
	 *         this list
	 */
	boolean contains(String id);

	/**
	 * Returns the index of the element with the given ID in this list. Returns
	 * -1 if this list doesn't contain such an element or the element
	 * hasn't been loaded yet.
	 * 
	 * @param id ID of the element to search for
	 * @return the index of the element with the given ID in this list, or
	 *         -1 if it doesn't exist
	 */
	int indexOf(String id);

}
