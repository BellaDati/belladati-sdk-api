package com.belladati.sdk.intervals;

import com.belladati.sdk.exception.interval.NullIntervalException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * A custom date or time interval. Custom intervals start and end at a
 * user-defined date/time.
 * 
 * @author Chris Hennigfeld
 */
public class CustomInterval<U extends IntervalUnit> extends Interval<U> {

	private final String start;
	private final String end;

	/**
	 * Creates a new custom interval.
	 * 
	 * @param intervalUnit the interval unit to use
	 * @param start start of the interval, in interval units from the current
	 *            date/time
	 * @param end end of the interval, in interval units from the current
	 *            date/time
	 * @throws NullIntervalException if the unit is <tt>null</tt>
	 */
	public CustomInterval(U intervalUnit, String start, String end) throws NullIntervalException {
		super(intervalUnit);
		if (start == null || end == null || start.trim().length() == 0 || end.trim().length() == 0) {
			throw new NullIntervalException(intervalUnit, "Missing start or end");
		}
		this.start = start;
		this.end = end;
	}

	/**
	 * Returns the start of the interval. This is returned exactly as given
	 * during interval creation and may or may not be syntactically valid.
	 * 
	 * @return the start of the interval
	 */
	public String getStart() {
		return start;
	}

	/**
	 * Returns the end of the interval. This is returned exactly as given during
	 * interval creation and may or may not be syntactically valid.
	 * 
	 * @return the end of the interval
	 */
	public String getEnd() {
		return end;
	}

	@Override
	ObjectNode buildIntervalNode() {
		ObjectNode node = new ObjectMapper().createObjectNode();

		node.put("from", start);
		node.put("to", end);
		node.put("type", "custom");

		return node;
	}
}
