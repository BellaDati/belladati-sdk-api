package com.belladati.sdk.intervals;

import com.belladati.sdk.exception.interval.InvalidAbsoluteIntervalException;
import com.belladati.sdk.exception.interval.InvalidRelativeIntervalException;
import com.belladati.sdk.exception.interval.NullIntervalException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * A relative date or time interval. Relative intervals start and end at a
 * date/time relative to the current date/time.
 * <p>
 * Start and end of a relative interval are numeric and indicate the number of
 * interval units from the current date/time. For example, creating an instance
 * using {@link DateUnit#MONTH} from -3 to -1 results in an
 * interval that includes the past 3 whole calendar months.
 * 
 * 
 */
public class RelativeInterval<U extends IntervalUnit> extends Interval<U> {

	private final int start;
	private final int end;

	/**
	 * Creates a new absolute interval.
	 * 
	 * @param intervalUnit the interval unit to use
	 * @param start start of the interval, in interval units from the current
	 *            date/time
	 * @param end end of the interval, in interval units from the current
	 *            date/time
	 * @throws NullIntervalException if the unit is null
	 * @throws InvalidAbsoluteIntervalException if the given start is after the
	 *             given end
	 */
	public RelativeInterval(U intervalUnit, int start, int end) throws NullIntervalException, InvalidAbsoluteIntervalException {
		super(intervalUnit);
		if (start > end) {
			throw new InvalidRelativeIntervalException(intervalUnit, start, end);
		}
		this.start = start;
		this.end = end;
	}

	/**
	 * Returns the start of the interval, in interval units relative to the
	 * current date/time.
	 * 
	 * @return the start of the interval
	 */
	public int getStart() {
		return start;
	}

	/**
	 * Returns the end of the interval, in interval units relative to the
	 * current date/time.
	 * 
	 * @return the end of the interval
	 */
	public int getEnd() {
		return end;
	}

	@Override
	ObjectNode buildIntervalNode() {
		ObjectNode node = new ObjectMapper().createObjectNode();

		node.put("from", start);
		node.put("to", end);
		node.put("type", "relative");

		return node;
	}
}
