package com.belladati.sdk.dashboard;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.belladati.sdk.util.Resource;

/**
 * A BellaDati dashboard. A dashboard holds a list of {@link Dashlet} objects
 * that are loaded as part of the dashboard.
 * 
 * @author Chris Hennigfeld
 */
public interface Dashboard extends Resource {

	/**
	 * Returns the last change date of this dashboard.
	 * 
	 * @return the last change date of this dashboard, or <tt>null</tt> if it
	 *         was never changed
	 */
	Date getLastChange();

	/**
	 * Loads a thumbnail image for the associated dashboard. The Java type of
	 * the image being returned depends on the implementation.
	 * 
	 * @return a thumbnail image for the associated dashboard
	 * @throws IOException if no image exists or it cannot be loaded
	 */
	Object loadThumbnail() throws IOException;

	/**
	 * Returns a list of dashlets in this dashboard.
	 * 
	 * @return a list of dashlets in this dashboard
	 */
	List<Dashlet> getDashlets();
}
