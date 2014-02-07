package com.belladati.sdk.report;

import java.io.IOException;
import java.util.Date;

import com.belladati.sdk.util.ResourceInfo;
import com.belladati.sdk.util.PaginatedList;

/**
 * Information object about a BellaDati report. Use this to get meta-information
 * about the report. To retrieve the report contents, call
 * {@link #loadDetails()}.
 * 
 * @author Chris Hennigfeld
 */
public interface ReportInfo extends ResourceInfo<Report> {

	/**
	 * Returns the associated report's description.
	 * 
	 * @return the associated report's description, or an empty string if no
	 *         description exists
	 */
	String getDescription();

	/**
	 * Returns the name of the associated report's owner.
	 * 
	 * @return the name of the associated report's owner
	 */
	String getOwnerName();

	/**
	 * Returns the last change date of the associated report.
	 * 
	 * @return the last change date of the associated report, or <tt>null</tt>
	 *         if it was never changed
	 */
	Date getLastChange();

	/**
	 * Loads a thumbnail image for the associated report. The Java type of the
	 * image being returned depends on the implementation.
	 * 
	 * @return a thumbnail image for the associated report
	 * @throws IOException if no image exists or it cannot be loaded
	 */
	Object loadThumbnail() throws IOException;

	/**
	 * Returns a paginated list to access the comments of the associated report.
	 * <p />
	 * Initially, the returned list is empty. Call {@link PaginatedList#load()}
	 * to load report data. Subsequent calls to this method or the associated
	 * report's method always return the same list.
	 * 
	 * @return a paginated list to access the comments of the associated report
	 */
	PaginatedList<Comment> getComments();

	/**
	 * Posts a comment to the associated report. To see the comment, reload the
	 * list returned from {@link #getComments()}.
	 * 
	 * @param text text of the comment to post
	 */
	void postComment(String text);
}
