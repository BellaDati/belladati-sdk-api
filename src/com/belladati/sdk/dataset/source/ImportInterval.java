package com.belladati.sdk.dataset.source;

import java.util.Date;

/**
 * The interval at which a {@link DataSourceImport} is executed.
 * 
 * @author Chris Hennigfeld
 */
public interface ImportInterval {

	/**
	 * Returns the number of minutes between subsequent executions.
	 * 
	 * @return the number of minutes between subsequent executions
	 */
	int getMinutes();

	/**
	 * Returns the next execution date/time.
	 * 
	 * @return the next execution date/time
	 */
	Date getNextExecution();
}
