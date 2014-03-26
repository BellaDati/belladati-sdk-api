package com.belladati.sdk.exception.interval;

import com.belladati.sdk.intervals.IntervalUnit;

/**
 * Indicates that the specified interval range was invalid.
 * 
 * @author Chris Hennigfeld
 */
public abstract class InvalidIntervalRangeException extends InvalidIntervalException {

	/** The serialVersionUID */
	private static final long serialVersionUID = 1577338871326585186L;

	InvalidIntervalRangeException(IntervalUnit intervalUnit, String message) {
		super(intervalUnit, message);
	}

	/**
	 * Returns the start of the interval that caused the problem.
	 * 
	 * @return the start of the interval
	 */
	public abstract Object getStart();

	/**
	 * Returns the end of the interval that caused the problem.
	 * 
	 * @return the end of the interval
	 */
	public abstract Object getEnd();
}