package com.belladati.sdk.dataset.source;

import com.belladati.sdk.dataset.data.OverwritePolicy;
import com.belladati.sdk.exception.server.NotFoundException;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * An import configuration from a data source to a data set being created by the
 * client.
 * 
 * @author Chris Hennigfeld
 */
public interface DataSourcePendingImport extends DataSourceImportBase {

	/**
	 * Sets the overwrite policy to use during this import. If none is set,
	 * {@link OverwritePolicy#deleteNone()} is used.
	 * 
	 * @param policy the overwrite policy to use
	 * @return this import, to allow chaining
	 * @throws IllegalStateException if the import has already been posted to
	 *             the server
	 */
	DataSourcePendingImport setOverwritePolicy(OverwritePolicy policy) throws IllegalStateException;

	/**
	 * Returns the overwrite policy set for this import.
	 * 
	 * @return the overwrite policy set for this import
	 */
	OverwritePolicy getOverwritePolicy();

	/**
	 * Sets the interval, in minutes, at which this import is repeated. By
	 * default, the import isn't repeated.
	 * <p />
	 * Set this number to <tt>0</tt> or a negative number to clear the interval.
	 * <p />
	 * This method has no corresponding getter. Call
	 * {@link #getRepeatInterval()} instead to retrieve the resulting interval.
	 * 
	 * @param minutes number of minutes between each subsequent import
	 * @return this import, to allow chaining
	 * @throws IllegalStateException if the import has already been posted to
	 *             the server
	 */
	DataSourcePendingImport setRepeatInterval(int minutes) throws IllegalStateException;

	/**
	 * Submits this import configuration to the server, triggering an import.
	 * After submission, all setter methods will throw
	 * {@link IllegalStateException}s.
	 * 
	 * @throws NotFoundException if the data source for this import
	 *             configuration doesn't exist on the server
	 * @throws IllegalStateException if the import has already been posted to
	 *             the server
	 */
	void post() throws NotFoundException, IllegalStateException;

	/**
	 * Returns a JSON representation of this pending import to send to the
	 * server. Used by the SDK internally.
	 * 
	 * @return a JSON representation of this pending import
	 */
	JsonNode toJson();
}
