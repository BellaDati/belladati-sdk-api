package com.belladati.sdk.dataset;

import java.util.Date;
import java.util.List;

import com.belladati.sdk.dataset.data.DataRow;
import com.belladati.sdk.dataset.data.DataTable;
import com.belladati.sdk.dataset.source.DataSource;
import com.belladati.sdk.exception.dataset.data.NoColumnsException;
import com.belladati.sdk.exception.dataset.data.UnknownServerColumnException;
import com.belladati.sdk.report.ReportInfo;
import com.belladati.sdk.util.CachedCollection;
import com.belladati.sdk.util.CachedList;
import com.belladati.sdk.util.Localizable;
import com.belladati.sdk.util.PaginatedIdList;
import com.belladati.sdk.util.PaginatedList;
import com.belladati.sdk.util.Resource;

/**
 * A BellaDati data set definition.
 * 
 * @author Chris Hennigfeld
 */
public interface DataSet extends Resource, Localizable {

	/**
	 * Returns the description of this data set.
	 * 
	 * @return the description of this data set
	 */
	String getDescription();

	/**
	 * Returns the name of the data set owner.
	 * 
	 * @return the name of the data set owner
	 */
	String getOwnerName();

	/**
	 * Returns the last change date of this data set.
	 * 
	 * @return the last change date of this data set, or <tt>null</tt> if it was
	 *         never changed
	 */
	Date getLastChange();

	/**
	 * Returns a list of all attributes used in this data set.
	 * 
	 * @return a list of all attributes used in this data set
	 */
	List<Attribute> getAttributes();

	/**
	 * Returns a list of all indicators used in this data set.
	 * 
	 * @return a list of all indicators used in this data set
	 */
	List<Indicator> getIndicators();

	/**
	 * Returns a list of all reports for this data set.
	 * 
	 * @return a list of all reports for this data set
	 */
	List<ReportInfo> getReports();

	/**
	 * Returns an empty data table matching this data set's structure. Populate
	 * this table in order to import data for the data set.
	 * 
	 * @return an empty data table matching this data set's structure
	 * @throws NoColumnsException if the data set doesn't have any columns
	 */
	DataTable createDataTable() throws NoColumnsException;

	/**
	 * Uploads the given data into this data set. This method doesn't perform
	 * any validation of the data; the caller should ensure it matches the data
	 * set's structure.
	 * <p>
	 * To ensure the structure is correct, generate the table calling
	 * {@link #createDataTable()} on the same data set.
	 * 
	 * @param data the data to upload
	 * @return this data set
	 * @throws UnknownServerColumnException if the data table contains a column
	 *             that doesn't exist in the data set
	 */
	DataSet uploadData(DataTable data) throws UnknownServerColumnException;

	/**
	 * Returns the list of data sources for this data set. This is a cached
	 * list, call {@link CachedCollection#loadFirstTime()} to populate it
	 * initially.
	 * 
	 * @return the list of data sources for this data set
	 */
	CachedList<DataSource> getDataSources();

	/**
	 * Returns a paginated list to access data set data visible to the current user.
	 * <p>
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load data set data.
	 * 
	 * @return a paginated list to access data set data visible to the current user
	 */
	PaginatedIdList<DataRow> getDataSetData();
}
