package com.belladati.sdk.util;

import java.util.Collection;

/**
 * A collection that is cached by the client. Call {@link #loadFirstTime()} to
 * initially load its contents from the server or {@link #load()} to clear the
 * cache and reload at a later time.
 * 
 * @author Chris Hennigfeld
 */
public interface CachedCollection<T, C extends Collection<T>> {

	/**
	 * Returns the immutable content of this cached collection.
	 * 
	 * @return the immutable content of this cached collection
	 */
	C get();

	/**
	 * Clears and loads the content of this collection from the server.
	 * 
	 * @return this collection (for chaining)
	 */
	CachedCollection<T, C> load();

	/**
	 * Loads the content of this collection from the server if it hasn't been
	 * loaded before. Calling this method is equivalent to:
	 * <p />
	 * <tt>if(!collection.isLoaded() { collection.load(); } </tt>
	 * 
	 * @return this collection (for chaining)
	 */
	CachedCollection<T, C> loadFirstTime();

	/**
	 * Returns <tt>true</tt> if this cached collection has been loaded from the
	 * server.
	 * 
	 * @return <tt>true</tt> if this cached collection has been loaded from the
	 *         server
	 */
	boolean isLoaded();
}
