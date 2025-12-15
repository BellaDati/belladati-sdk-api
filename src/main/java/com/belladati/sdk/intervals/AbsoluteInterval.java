package com.belladati.sdk.intervals;

import java.util.Calendar;

import com.belladati.sdk.exception.interval.InvalidAbsoluteIntervalException;
import com.belladati.sdk.exception.interval.NullIntervalException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * An absolute date or time interval. Absolute intervals start and end at a
 * specific, fixed date or time.
 * <p>
 * Note that even though start and end of the interval are specified as whole
 * dates, the relevant portion is determined by the interval unit.<br>
 * For example, creating an instance using {@link DateUnit#MONTH} from
 * 2014-01-04 to 2014-03-08 will result in an interval
 * including the whole months of January through March, i.e. from
 * 2014-01-01 to 2014-03-31.
 * 
 * 
 */
public class AbsoluteInterval<U extends IntervalUnit> extends Interval<U> {

	private final long start;
	private final long end;

	/**
	 * Creates a new absolute interval.
	 * 
	 * @param intervalUnit the interval unit to use, indicating date or time and
	 *            the highest level of detail
	 * @param start start of the interval, ignoring any parts more detailed than
	 *            the interval unit
	 * @param end end of the interval, ignoring any parts more detailed than the
	 *            interval unit
	 * @throws NullIntervalException if start, end, or unit are null
	 * @throws InvalidAbsoluteIntervalException if the given start is after the
	 *             given end
	 */
	public AbsoluteInterval(U intervalUnit, Calendar start, Calendar end)
		throws NullIntervalException, InvalidAbsoluteIntervalException {
		super(intervalUnit);
		if (start == null || end == null) {
			throw new NullIntervalException(intervalUnit, "Interval start and end may not be null");
		}
		if (start.compareTo(end) > 0) {
			throw new InvalidAbsoluteIntervalException(intervalUnit, start, end);
		}
		this.start = start.getTimeInMillis();
		this.end = end.getTimeInMillis();
	}

	/**
	 * Returns the start of the interval. This is the whole date that was
	 * specified during instantiation, even if it's too detailed for the given
	 * interval unit.
	 * 
	 * @return the start of the interval
	 */
	public Calendar getStart() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(start);
		return cal;
	}

	/**
	 * Returns the end of the interval. This is the whole date that was
	 * specified during instantiation, even if it's too detailed for the given
	 * interval unit.
	 * 
	 * @return the start of the interval
	 */
	public Calendar getEnd() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(end);
		return cal;
	}

	@Override
	ObjectNode buildIntervalNode() {
		ObjectNode node = new ObjectMapper().createObjectNode();

		node.put("from", intervalUnit.buildAbsoluteNode(start));
		node.put("to", intervalUnit.buildAbsoluteNode(end));
		node.put("type", "absolute");

		return node;
	}
}
