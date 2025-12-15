package com.belladati.sdk.exception.auth;

/**
 * Indicates that a request's OAuth timestamp was rejected by the server. This
 * is caused by having a different time set on the server and SDK client
 * computer.
 * <p>
 * If this exception occurs, verify that both server and client computer are set
 * to the correct time for their respective time zone.
 * <p>
 * You can find out the time on the server when the request was received by
 * calculating the average of {@link #getEarliestTimestamp()} and
 * {@link #getLatestTimestamp()}.
 * 
 * 
 */
public class InvalidTimestampException extends AuthorizationException {
	/** The serialVersionUID */
	private static final long serialVersionUID = -3164294608086460274L;

	private final long earliestTimestamp;
	private final long latestTimestamp;

	/**
	 * Creates a new instance with the given accepted timestamp range.
	 * 
	 * @param earliestTimestamp the earliest acceptable timestamp as returned by
	 *            the server
	 * @param latestTimestamp the latest acceptable timestamp as returned by the
	 *            server
	 */
	public InvalidTimestampException(long earliestTimestamp, long latestTimestamp) {
		super(Reason.TIMESTAMP_REFUSED, "Valid timestamps are " + earliestTimestamp + " to " + latestTimestamp);
		this.earliestTimestamp = earliestTimestamp;
		this.latestTimestamp = latestTimestamp;
	}

	/**
	 * Returns the earliest acceptable timestamp as provided by the server. Note
	 * that when making a new request some time will have passed, meaning this
	 * timestamp likely won't be acceptable any more.
	 * 
	 * @return the earliest acceptable timestamp as provided by the server
	 */
	public long getEarliestTimestamp() {
		return earliestTimestamp;
	}

	/**
	 * Returns the latest acceptable timestamp as provided by the server.
	 * 
	 * @return the latest acceptable timestamp as provided by the server
	 */
	public long getLatestTimestamp() {
		return latestTimestamp;
	}
}
