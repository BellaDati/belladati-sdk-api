package com.belladati.sdk.dataset.source;

import com.belladati.sdk.util.IdElement;

/**
 * A single execution of an import from a data source to a data set. Can be
 * either a definition retrieved from the server or one being defined by the
 * client.
 * 
 * 
 */
public interface DataSourceImportBase extends IdElement {

	/**
	 * Returns the interval at which this import is executed repeatedly.
	 * 
	 * @return the interval at which this import is executed, or null
	 *         if this import isn't executed periodically
	 */
	ImportInterval getRepeatInterval();

	/**
	 * Returns true if this import has an overwrite policy and may
	 * overwrite data. Whether or not data is actually overwritten depends on
	 * the details of the override policy (not currently accessible through the
	 * API).
	 * 
	 * @return true if this import has an overwrite policy
	 */
	boolean isOverwriting();
}
