package com.belladati.sdk.exception.interval;

import com.belladati.sdk.intervals.IntervalUnit;

/**
 * Indicates that a parameter was null during interval creation. This
 * can be the interval's unit, start, or end.
 * 
 * 
 */
public class NullIntervalException extends InvalidIntervalException {

	/** The serialVersionUID */
	private static final long serialVersionUID = 8579126864594603415L;

	public NullIntervalException(IntervalUnit intervalUnit, String message) {
		super(intervalUnit, message);
	}
}
