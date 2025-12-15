package com.belladati.sdk.exception.interval;

import java.util.Calendar;

import com.belladati.sdk.intervals.IntervalUnit;

/**
 * Indicates that an absolute interval had an invalid range, i.e. the start is
 * before the end.
 * 
 * 
 */
public class InvalidAbsoluteIntervalException extends InvalidIntervalRangeException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -610277881390602277L;

	private final Calendar start;
	private final Calendar end;

	public InvalidAbsoluteIntervalException(IntervalUnit intervalUnit, Calendar start, Calendar end) {
		super(intervalUnit, "Interval start cannot be before its end");
		this.start = start;
		this.end = end;
	}

	@Override
	public Calendar getStart() {
		return start;
	}

	@Override
	public Calendar getEnd() {
		return end;
	}
}