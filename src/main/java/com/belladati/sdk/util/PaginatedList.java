package com.belladati.sdk.util;

import java.util.List;

/**
 * A list of elements that is returned by the server in paginated form. With
 * each request, the server returns up to a maximum amount of elements. Use
 * {@link #load()} to load the first page of the list and {@link #loadNext()}
 * for subsequent pages.
 * <p>
 * To directly load a page other than the first or to specify the page size, use
 * the overloaded forms {@link #load(int)} and {@link #load(int, int)}.
 * 
 * 
 */
public interface PaginatedList<T> extends Iterable<T> {

	/**
	 * Loads the first page of data from BellaDati. The server's default page
	 * size is used.
	 * 
	 * @return this list, to allow chaining
	 */
	PaginatedList<T> load();

	/**
	 * Loads the first page of data from BellaDati, using the given page size.
	 * <p>
	 * If the specified page size is larger than the server's maximum page size,
	 * the maximum page size is used instead.
	 * 
	 * @param size size to use in pagination
	 * @return this list, to allow chaining
	 * @throws IllegalArgumentException if size &lt;= 0
	 */
	PaginatedList<T> load(int size) throws IllegalArgumentException;

	/**
	 * Loads the given page of data from BellaDati, using the given page size.
	 * The index of the first page is 0.
	 * <p>
	 * If the specified page size is larger than the server's maximum page size,
	 * the maximum page size is used instead.
	 * 
	 * @param page index of the requested page
	 * @param size size to use in pagination
	 * @return this list, to allow chaining
	 * @throws IllegalArgumentException if page &lt; 0 || size &lt;= 0
	 */
	PaginatedList<T> load(int page, int size) throws IllegalArgumentException;

	/**
	 * Loads the next page of data from BellaDati, using the page size from the
	 * most recent call to {@link #load()}, {@link #load(int)} or
	 * {@link #load(int, int)}.
	 * <p>
	 * If no load method has been called on this list yet, calling this method
	 * is equivalent to calling {@link #load()} without parameters.
	 * 
	 * @return this list, to allow chaining
	 */
	PaginatedList<T> loadNext();

	/**
	 * Returns true if this list has been loaded, i.e. any of the load
	 * methods has been called at least once.
	 * 
	 * @return true if this list has been loaded
	 */
	boolean isLoaded();

	/**
	 * Returns true if there may be more pages of data available after
	 * the last loaded page.
	 * <p>
	 * Specifically, this method returns true under either of two
	 * circumstances:
	 * <ul>
	 * <li>this list has never been loaded</li>
	 * <li>this list has been loaded, and the last page contains
	 * {@link #getPageSize()} elements</li>
	 * </ul>
	 * 
	 * @return true if there may be more pages of data available after
	 *         the last loaded page
	 */
	boolean hasNextPage();

	/**
	 * Returns the index of the first page loaded in this list, or -1
	 * if the list hasn't been loaded. This is the index of the page specified
	 * during the most recent call to any load method other than loadNext, or
	 * 0 if no first page was specified.
	 * 
	 * @return the index of the first page loaded in this list
	 */
	int getFirstLoadedPage();

	/**
	 * Returns the index of the last page loaded in this list, or -1 if
	 * the list hasn't been loaded. Use {@link #hasNextPage()} to find out if
	 * more pages are available, and {@link #loadNext()} to load more pages.
	 * 
	 * @return the index of the last page loaded in this list
	 */
	int getLastLoadedPage();

	/**
	 * Returns the index of the first item loaded in this list, or -1
	 * if the list is empty or hasn't been loaded.
	 * 
	 * @return the index of the first item loaded in this list
	 */
	int getFirstLoadedIndex();

	/**
	 * Returns the index of the last item loaded in this list, or -1 if
	 * the list is empty or hasn't been loaded.
	 * 
	 * @return the index of the last item loaded in this list
	 */
	int getLastLoadedIndex();

	/**
	 * Returns the page size used by this list. This is the index of the page
	 * specified during the most recent call to any load method, or the server
	 * default if none was specified.
	 * <p>
	 * If this list hasn't been loaded yet, this method returns -1.
	 * 
	 * @return the page size used by this list
	 */
	int getPageSize();

	/**
	 * Returns true if the given element has been loaded in this list.
	 * Note that this method returns false if the element exists but on
	 * a page that hasn't yet been loaded from the server.
	 * 
	 * @param element the element to look for
	 * @return true if the given element has been loaded in this list
	 */
	boolean contains(T element);

	/**
	 * Returns the element at the given index. The first element's index is
	 * 0.
	 * <p>
	 * The index refers to the absolute index in the collection. For example,
	 * after calling load(2, 20), the first available index is
	 * 40.
	 * 
	 * @param index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the given index hasn't been loaded,
	 *             or the index is out of range
	 */
	T get(int index) throws IndexOutOfBoundsException;

	/**
	 * Returns the index of the given element in this list. Returns -1
	 * if this list doesn't contain the element or the element hasn't been
	 * loaded yet.
	 * 
	 * @param element element to search for
	 * @return the index of the given element in this list, or -1 if it
	 *         doesn't exist
	 */
	int indexOf(T element);

	/**
	 * Returns true if this list contains no elements. This could be
	 * because there are no elements, or because this list hasn't been loaded
	 * yet.
	 * 
	 * @return true if this list contains no elements
	 */
	boolean isEmpty();

	/**
	 * Returns the size of the list as currently loaded.
	 * <p>
	 * Specifically, for a non-empty list this is
	 * {@link #getLastLoadedIndex()} - {@link #getFirstLoadedIndex()} + 1
	 * .
	 * 
	 * @return the size of the list as currently loaded
	 */
	int size();

	/**
	 * Returns the currently loaded contents of this list as an immutable
	 * {@link List}. Returns an empty list if this list was never loaded.
	 * 
	 * @return the currently loaded contents of this list as an immutable
	 *         {@link List}
	 */
	List<T> toList();
}
