package com.belladati.sdk.util;

import java.util.List;

/**
 * A list that is cached by the client, a special case of a
 * {@link CachedCollection}. Call {@link #load()} to initially load its contents
 * from the server or to clear the cache and reload at a later time.
 * 
 * @author Chris Hennigfeld
 */
public interface CachedList<T> extends CachedCollection<T, List<T>> {

	/**
	 * Clears and loads the content of this list from the server.
	 * 
	 * @return this list (for chaining)
	 */
	@Override
	CachedList<T> load();

	/**
	 * Loads the content of this list from the server if it hasn't been loaded
	 * before. Calling this method is equivalent to:
	 * <p>
	 * <tt>if(!list.isLoaded() { list.load(); } </tt>
	 * 
	 * @return this list (for chaining)
	 */
	@Override
	CachedList<T> loadFirstTime();

	/**
	 * Synonymous to {@link #get()}, returns this CachedList's contents as an
	 * immutable List.
	 * 
	 * @return this CachedList's contents as an immutable List
	 */
	List<T> toList();
}
