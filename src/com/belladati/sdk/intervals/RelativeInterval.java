package com.belladati.sdk.intervals;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class RelativeInterval<U extends IntervalUnit> extends Interval<U> {

	private final int start;
	private final int end;

	public RelativeInterval(U intervalUnit, int start, int end) {
		super(intervalUnit);
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	@Override
	ObjectNode buildIntervalNode() {
		// TODO Auto-generated method stub
		return null;
	}
}
