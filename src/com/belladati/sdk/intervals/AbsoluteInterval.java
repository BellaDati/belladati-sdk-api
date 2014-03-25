package com.belladati.sdk.intervals;

import java.util.Calendar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AbsoluteInterval<U extends IntervalUnit> extends Interval<U> {

	private final long start;
	private final long end;

	public AbsoluteInterval(U intervalUnit, Calendar start, Calendar end) {
		super(intervalUnit);
		this.start = start.getTimeInMillis();
		this.end = end.getTimeInMillis();
	}

	public Calendar getStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(start);
		return cal;
	}

	public Calendar getEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(end);
		return cal;
	}

	@Override
	ObjectNode buildIntervalNode() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();

		node.put("from", intervalUnit.buildAbsoluteNode(start));
		node.put("to", intervalUnit.buildAbsoluteNode(end));
		node.put("type", "absolute");

		return node;
	}
}
