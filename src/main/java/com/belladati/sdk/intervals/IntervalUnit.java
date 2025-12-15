package com.belladati.sdk.intervals;

import java.util.Calendar;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * A unit that can be used in date/time intervals. This interface is implemented
 * by {@link DateUnit} and {@link TimeUnit}, which contain all interval units
 * currently supported by BellaDati.
 * 
 * 
 */
public interface IntervalUnit {
	/**
	 * Returns this unit's internal name. This method name is intended to match
	 * {@link Enum#name()} since implementing classes are meant to be
	 * enum.
	 * 
	 * @return this unit's internal name
	 */
	String name();

	/**
	 * Builds an interval node for an absolute interval using this unit and the
	 * given timestamp. Depending on the unit, the resulting node will have
	 * different fields set.
	 * 
	 * @param timestamp timestamp to turn into an interval node
	 * @return an interval node for an absolute interval using this unit and the
	 *         given timestamp
	 */
	JsonNode buildAbsoluteNode(long timestamp);

	/**
	 * Parses an absolute date/time into a calendar. Fields not set in the unit
	 * (e.g. the date part in a time unit) are set to 0.
	 * 
	 * @param node interval node to parse
	 * @return the resulting interval unit
	 */
	Calendar parseAbsolute(JsonNode node);

	/**
	 * Returns the node name, i.e. dateInterval or
	 * timeInterval.
	 * 
	 * @return the node name
	 */
	String getUnitNodeName();
}
