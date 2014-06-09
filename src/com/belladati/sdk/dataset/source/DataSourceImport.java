package com.belladati.sdk.dataset.source;

import java.util.Date;

/**
 * A single execution of an import from a data source to a data set as set on
 * the server.
 * 
 * @author Chris Hennigfeld
 */
public interface DataSourceImport extends DataSourceImportBase {

	/**
	 * Returns the date and time on which this import was last executed.
	 * 
	 * @return the date and time on which this import was last executed
	 */
	Date getLastExecutionDate();

	/**
	 * Returns the name of the user who triggered this import.
	 * 
	 * @return the name of the user who triggered this import
	 */
	String getCallerName();
}
