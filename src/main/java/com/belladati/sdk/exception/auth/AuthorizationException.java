package com.belladati.sdk.exception.auth;

import com.belladati.sdk.exception.server.ServerResponseException;

/**
 * Thrown if something went wrong during authentication or authorization. Check
 * {@link #getReason()} to find out more.
 * 
 * @author Chris Hennigfeld
 */
public class AuthorizationException extends ServerResponseException {

	/** The serialVersionUID */
	private static final long serialVersionUID = 1500621037588354229L;

	/**
	 * Possible causes of an {@link AuthorizationException}. When dealing with
	 * an {@link AuthorizationException}, use its reason to display an
	 * appropriate message to the user.
	 * <p>
	 * <b>Note:</b> As of BellaDati 2.7.6, not all reasons are currently
	 * supported by the server.
	 * 
	 * @author Chris Hennigfeld
	 */
	public enum Reason {

		/** Consumer key doesn't exist on the server. */
		CONSUMER_KEY_UNKNOWN("Consumer key rejected by server"),

		/** Consumer secret doesn't match the consumer key. */
		CONSUMER_SECRET_INVALID("Consumer secret doesn't match consumer key"),

		/** Domain license has expired. */
		DOMAIN_EXPIRED("Domain has expired"),

		/** Request or access token is invalid, may require re-authentication. */
		TOKEN_INVALID("Invalid token"),

		/** Request token hasn't been authorized. */
		TOKEN_UNAUTHORIZED("Unauthorized token"),

		/** Request or access token has expired, requires re-authentication. */
		TOKEN_EXPIRED("Token has expired"),

		/**
		 * Timestamp doesn't match the server's time. This exception is an
		 * instance of {@link InvalidTimestampException}, cast to get accepted
		 * timestamp range.
		 */
		TIMESTAMP_REFUSED("Timestamp rejected by server"),

		/** xAuth only: username doesn't exist or password was incorrect. */
		USER_CREDENTIALS_INVALID("Incorrect username or password"),

		/** xAuth only: credentials are correct but user is locked. */
		USER_ACCOUNT_LOCKED("User account locked"),

		/** xAuth only: credentials are correct but user isn't in the domain. */
		USER_DOMAIN_MISMATCH("User doesn't have access to domain"),

		/** xAuth only: xAuth isn't enabled for the domain. */
		X_AUTH_DISABLED("xAuth is not enabled for the domain"),

		/**
		 * BellaDati Mobile only: BellaDati Mobile access isn't enabled for the
		 * domain.
		 */
		BD_MOBILE_DISABLED("BellaDati Mobile is not enabled for the domain"),

		/**
		 * An uncategorized error occurred. Refer to the exception's message for
		 * details.
		 */
		OTHER("Unknown OAuth error");

		private final String message;

		private Reason(String message) {
			this.message = message;
		}

		/**
		 * Returns an English-language description of this reason.
		 * 
		 * @return an English-language description of this reason
		 */
		public String getMessage() {
			return message;
		}
	}

	private final Reason reason;

	/**
	 * Creates a new instance with the given reason. The exception message
	 * depends on the reason's message.
	 * 
	 * @param reason reason for the exception
	 */
	public AuthorizationException(Reason reason) {
		super(reason.message);
		this.reason = reason;
	}

	/**
	 * Creates a new instance with the given reason and message. The exception
	 * message will contain both the reason's message and the custom message.
	 * 
	 * @param reason reason for the exception
	 * @param message custom exception message, use this to express details not
	 *            covered by the reason alone
	 */
	public AuthorizationException(Reason reason, String message) {
		super(reason.message + ". " + message);
		this.reason = reason;
	}

	/**
	 * Returns the detailed reason for this exception. Typically, different
	 * reasons should result in different error messages for the user.
	 * 
	 * @return the detailed reason for this exception
	 */
	public Reason getReason() {
		return reason;
	}
}
