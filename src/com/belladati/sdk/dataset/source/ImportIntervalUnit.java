package com.belladati.sdk.dataset.source;

/**
 * Units of time at which import intervals can be defined.
 * 
 * @author Chris Hennigfeld
 */
public enum ImportIntervalUnit {
	MINUTE(1), HOUR(60 * MINUTE.minutes), DAY(24 * HOUR.minutes), WEEK(7 * DAY.minutes), MONTH(31 * DAY.minutes), QUARTER(
		3 * MONTH.minutes), YEAR(365 * DAY.minutes);

	private final int minutes;

	private ImportIntervalUnit(int minutes) {
		this.minutes = minutes;
	}

	/**
	 * Returns the number of minutes in this time unit. Used internally inside
	 * the SDK.
	 * 
	 * @return the number of minutes in this time unit
	 */
	public int getMinutes() {
		return minutes;
	}
}
