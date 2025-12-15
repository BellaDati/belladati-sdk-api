package com.belladati.sdk.dataset.source;

/**
 * The interval at which a {@link DataSourceImport} is executed.
 * 
 * 
 */
public interface ImportInterval {

	/**
	 * Returns the time unit at which this interval repeats. Repeat time is
	 * calculated as {@link #getUnit()} * {@link #getFactor()}.
	 * 
	 * @return the time unit at which this interval repeats
	 */
	ImportIntervalUnit getUnit();

	/**
	 * Returns the multiple of time units at which this interval repeats. Repeat
	 * time is calculated as {@link #getUnit()} * {@link #getFactor()}.
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
