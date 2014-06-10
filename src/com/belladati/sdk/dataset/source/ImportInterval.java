package com.belladati.sdk.dataset.source;


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
}
