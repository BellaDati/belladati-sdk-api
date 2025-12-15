package com.belladati.sdk.dataset;

import java.util.Date;

import com.belladati.sdk.dataset.data.DataRow;
import com.belladati.sdk.util.Localizable;
import com.belladati.sdk.util.PaginatedIdList;
import com.belladati.sdk.util.PaginatedList;
import com.belladati.sdk.util.ResourceInfo;

/**
 * Information object about a BellaDati data set. Use this to get
 * meta-information about the data set. To retrieve the report contents, call
 * {@link #loadDetails()}.
 * 
 * 
 */
public interface DataSetInfo extends ResourceInfo<DataSet>, Localizable {

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
	 * @return the last change date of the associated data set, or null
	 *         if it was never changed
	 */
	Date getLastChange();

	/**
	 * Returns a paginated list to access data set data visible to the current user.
	 * <p>
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load data set data.
	 * 
	 * @return a paginated list to access data set data visible to the current user
	 */
	PaginatedIdList<DataRow> getData();

	/**
	 * Posts a row into this data set. If the row has "id" then existing row with this
	 * identification will be updated. Otherwise, new row will be added.
	 * 
	 * @param row data row to post
	 */
	void postData(DataRow row);

}
