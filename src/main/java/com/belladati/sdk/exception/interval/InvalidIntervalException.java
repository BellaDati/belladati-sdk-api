package com.belladati.sdk.exception.interval;

import com.belladati.sdk.exception.BellaDatiRuntimeException;
import com.belladati.sdk.intervals.IntervalUnit;

/**
 * Indicates that something went wrong with an interval.
 * 
 * @author Chris Hennigfeld
 */
public abstract class InvalidIntervalException extends BellaDatiRuntimeException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -7118929916818931716L;

	private final IntervalUnit intervalUnit;

	InvalidIntervalException(IntervalUnit intervalUnit, String message) {
		super(message);
		this.intervalUnit = intervalUnit;
	}

	/**
	 * Returns the unit set on the interval that caused the problem.
	 * 
	 * @return the unit set on the interval
	 */
	public IntervalUnit getIntervalUnit() {
		return intervalUnit;
	}
}
