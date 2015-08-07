package com.belladati.sdk.util;

/**
 * A paginated list containing elements with an ID. Provides additional methods
 * to easily find elements with a given ID in the list.
 * 
 * @author Chris Hennigfeld
 */
public interface PaginatedIdList<T extends IdElement> extends PaginatedList<T> {

	/**
	 * Returns <tt>true</tt> if an element with the given ID has been loaded in
	 * this list. Note that this method returns <tt>false</tt> if such an
	 * element exists but on a page that hasn't yet been loaded from the server.
	 * 
	 * @param id ID of the element to look for
	 * @return <tt>true</tt> if an element with the given ID has been loaded in
	 *         this list
	 */
	boolean contains(String id);

	/**
	 * Returns the index of the element with the given ID in this list. Returns
	 * <tt>-1</tt> if this list doesn't contain such an element or the element
	 * hasn't been loaded yet.
	 * 
	 * @param id ID of the element to search for
	 * @return the index of the element with the given ID in this list, or
	 *         <tt>-1</tt> if it doesn't exist
	 */
	int indexOf(String id);

}
