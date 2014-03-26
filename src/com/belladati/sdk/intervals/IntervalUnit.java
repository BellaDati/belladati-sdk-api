package com.belladati.sdk.intervals;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * A unit that can be used in date/time intervals. This interface is implemented
 * by {@link DateUnit} and {@link TimeUnit}, which contain all interval units
 * currently supported by BellaDati.
 * 
 * @author Chris Hennigfeld
 */
public interface IntervalUnit {
	/**
	 * Returns this unit's internal name. This method name is intended to match
	 * {@link Enum#name()} since implementing classes are meant to be
	 * <tt>enum</tt>.
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
	 * Returns the node name, i.e. <tt>dateInterval</tt> or
	 * <tt>timeInterval</tt>.
	 * 
	 * @return the node name
	 */
	String getUnitNodeName();
}
