package com.belladati.sdk.exception.interval;

import com.belladati.sdk.intervals.IntervalUnit;

/**
 * Indicates that an absolute interval had an invalid range, i.e. the start is
 * before the end.
 * 
 * @author Chris Hennigfeld
 */
public class InvalidRelativeIntervalException extends InvalidIntervalRangeException {

	/** The serialVersionUID */
	private static final long serialVersionUID = -7249403016857084659L;

	private final int start;
	private final int end;

	public InvalidRelativeIntervalException(IntervalUnit intervalUnit, int start, int end) {
		super(intervalUnit, "Interval start cannot be before its end");
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer getStart() {
		return start;
	}

	@Override
	public Integer getEnd() {
		return end;
	}
}