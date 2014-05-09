package com.belladati.sdk.dataset;

import java.util.Date;

import com.belladati.sdk.util.ResourceInfo;

/**
 * Information object about a BellaDati data set. Use this to get
 * meta-information about the data set. To retrieve the report contents, call
 * {@link #loadDetails()}.
 * 
 * @author Chris Hennigfeld
 */
public interface DataSetInfo extends ResourceInfo<DataSet> {

	/**
	 * Returns the associated data set's description.
	 * 
	 * @return the associated data set's description, or an empty string if no
	 *         description exists
	 */
	String getDescription();

	/**
	 * Returns the name of the associated data set's owner.
	 * 
	 * @return the name of the associated data set's owner
	 */
	String getOwnerName();

	/**
	 * Returns the last change date of the associated data set.
	 * 
	 * @return the last change date of the associated data set, or <tt>null</tt>
	 *         if it was never changed
	 */
	Date getLastChange();

}