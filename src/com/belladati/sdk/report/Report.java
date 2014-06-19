package com.belladati.sdk.report;

import java.util.Date;
import java.util.List;

import com.belladati.sdk.dataset.Attribute;
import com.belladati.sdk.dataset.DataSetInfo;
import com.belladati.sdk.util.Localizable;
import com.belladati.sdk.util.PaginatedList;
import com.belladati.sdk.util.Resource;
import com.belladati.sdk.view.View;

/**
 * A BellaDati report. A report holds a list of {@link View} objects that are
 * loaded as part of the report.
 * 
 * @author Chris Hennigfeld
 */
public interface Report extends Resource, Localizable {

	/**
	 * Returns the description of this report.
	 * 
	 * @return the description of this report
	 */
	String getDescription();

	/**
	 * Returns the name of the report owner.
	 * 
	 * @return the name of the report owner
	 */
	String getOwnerName();

	/**
	 * Returns the last change date of this report.
	 * 
	 * @return the last change date of this report, or <tt>null</tt> if it was
	 *         never changed
	 */
	Date getLastChange();

	/**
	 * Returns a list of views in this report.
	 * 
	 * @return a list of views in this report
	 */
	List<View> getViews();

	/**
	 * Returns a list of all attributes used in this report. You can use these
	 * attributes to filter the report's views.
	 * 
	 * @return a list of all attributes used in this report
	 */
	List<Attribute> getAttributes();

	/**
	 * Returns a paginated list to access the comments of this report.
	 * <p />
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load report data. Subsequent calls to this method on a report always
	 * return the same list.
	 * 
	 * @return a paginated list to access the comments of this report
	 */
	PaginatedList<Comment> getComments();

	/**
	 * Posts a comment to this report. To see the comment, reload the list
	 * returned from {@link #getComments()}.
	 * 
	 * @param text text of the comment to post
	 */
	void postComment(String text);

	/**
	 * Returns the data set this report is built on.
	 * 
	 * @return the data set this report is built on
	 */
	DataSetInfo getDataSet();
}
