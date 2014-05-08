package com.belladati.sdk.dataset;

import java.util.Date;
import java.util.List;

import com.belladati.sdk.report.ReportInfo;
import com.belladati.sdk.util.Resource;

public interface DataSet extends Resource {

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
}
