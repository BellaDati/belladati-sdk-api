package com.belladati.sdk.dashboard;

import java.util.Date;

import com.belladati.sdk.exception.server.InvalidStreamException;
import com.belladati.sdk.util.ResourceInfo;

/**
 * Information object about a BellaDati dashboard. Use this to get
 * meta-information about the dashboard. To retrieve the dashboard contents,
 * call {@link #loadDetails()}.
 * 
 * @author Chris Hennigfeld
 */
public interface DashboardInfo extends ResourceInfo<Dashboard> {

	/**
	 * Returns the last change date of the associated dashboard.
	 * 
	 * @return the last change date of the associated dashboard, or
	 *         <tt>null</tt> if it was never changed
	 */
	Date getLastChange();

	/**
	 * Loads a thumbnail image for the associated dashboard. The Java type of
	 * the image being returned depends on the implementation.
	 * 
	 * @return a thumbnail image for the associated dashboard
	 * @throws InvalidStreamException if no image exists or it cannot be loaded
	 */
	Object loadThumbnail() throws InvalidStreamException;
}
