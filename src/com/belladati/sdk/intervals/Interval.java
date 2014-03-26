package com.belladati.sdk.intervals;

import com.belladati.sdk.exception.interval.NullIntervalException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * An interval that can be used when loading a view's content. Intervals allow
 * you to limit the range of data used in a view in the same way as setting date
 * or time intervals in the BellaDati frontend.
 * 
 * @author Chris Hennigfeld
 */
public abstract class Interval<U extends IntervalUnit> {

	/** the unit for the interval, indicating the level of detail */
	protected final U intervalUnit;

	Interval(U intervalUnit) throws NullIntervalException {
		if (intervalUnit == null) {
			throw new NullIntervalException(intervalUnit, "Interval unit may not be null");
		}
		this.intervalUnit = intervalUnit;
	}

	/**
	 * Returns the date or time unit used in this interval. In a relative
	 * interval, this is the unit used for the relative numbers. In an absolute
	 * interval, the unit indicates the highest level of detail used.
	 * 
	 * @return the date or time unit used in this interval
	 */
	public U getIntervalUnit() {
		return intervalUnit;
	}

	/**
	 * Returns a JSON representation of this interval to send to the server.
	 * Used by the SDK internally.
	 * 
	 * @return a JSON representation of this interval
	 */
	public ObjectNode toJson() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		ObjectNode unitIntervalNode = mapper.createObjectNode();

		node.put(intervalUnit.getUnitNodeName(), unitIntervalNode);
		unitIntervalNode.put("interval", buildIntervalNode());
		unitIntervalNode.put("aggregationType", intervalUnit.name());

		return node;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}

	/**
	 * Builds the interval node containing the from/to definition.
	 * 
	 * @return the interval node containing the from/to definition
	 */
	abstract ObjectNode buildIntervalNode();
}