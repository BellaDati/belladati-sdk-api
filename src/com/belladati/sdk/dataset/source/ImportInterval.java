package com.belladati.sdk.dataset.source;

/**
 * The interval at which a {@link DataSourceImport} is executed.
 * 
 * @author Chris Hennigfeld
 */
public interface ImportInterval {

	/**
	 * Returns the time unit at which this interval repeats. Repeat time is
	 * calculated as <tt>{@link #getUnit()} * {@link #getFactor()}</tt>.
	 * 
	 * @return the time unit at which this interval repeats
	 */
	ImportIntervalUnit getUnit();

	/**
	 * Returns the multiple of time units at which this interval repeats. Repeat
	 * time is calculated as <tt>{@link #getUnit()} * {@link #getFactor()}</tt>.
	 * 
	 * @return the multiple of time units at which this interval repeats
	 */
	int getFactor();

	/**
	 * Returns the number of minutes between subsequent executions.
	 * 
	 * @return the number of minutes between subsequent executions
	 */
	int getMinutes();
}
